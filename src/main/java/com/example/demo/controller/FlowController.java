package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.FlowGenDataConstants;
import com.example.demo.domain.DataCounter;
import com.example.demo.domain.DataNode;
import com.example.demo.service.KafkaService;
import com.example.demo.utils.PlatformDataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cgj 类说明
 */
@RestController
@RequestMapping("/flow")
@Slf4j
public class FlowController implements InitializingBean {

    @Value("${flow.url:http://10.100.1.169}")
    private String url;
    @Value("${flow.sys.channel:test1}")
    private String sysChannel;
    @Resource
    private KafkaService kafkaService;
    private LinkedBlockingQueue<DataNode> inputQueue;
    private RestTemplate template;
    private boolean flag;
    private int concurrentHandleRecordCount;
    private ExecutorService threadPool;

    @GetMapping("/{limit}")
    public String send(@PathVariable int limit) throws InterruptedException {
        if(flag) {
            return "请等上次测试结果结束，再发送压测请求";
        }
        long d1 = System.currentTimeMillis();
        flag = true;
        DataCounter dataCounter = new DataCounter();
        dataCounter.getAll().set(limit);

        for (int i = 0; i < concurrentHandleRecordCount; i++) {
            threadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "：线程启动处理数据");
                //消费队列中的dfp
                while (true) {
                    try {
                        doSend(dataCounter, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        threadPool.submit(() -> {
            for (int i = 0; i < limit/10; i++) {
                try {
                    JSONObject dfpObj = new JSONObject();
                    List<Map<String, Object>> list = new ArrayList<>();
                    Map<String, Object> map = PlatformDataUtils.AND_MAP;
                    String dfp = UUID.randomUUID().toString().replaceAll("-", "");
                    map.put(FlowGenDataConstants.DFP, dfp);
                    map.put(FlowGenDataConstants.OUT_CODE, UUID.randomUUID().toString() + UUID.randomUUID().toString());
                    list.add(map);
                    dfpObj.put(FlowGenDataConstants.DFP_FLOWS, list);
                    kafkaService.send(dfpObj.toJSONString());
                    //往队列中加入这条数据提供消费
                    inputQueue.put(new DataNode(System.currentTimeMillis(), dfp, dfpObj));

                    doSend(dataCounter, new DataNode(System.currentTimeMillis(), dfp, dfpObj));
                } catch (InterruptedException e) {
                    dataCounter.getFail().incrementAndGet();
                }
            }
        });
        System.out.println(System.currentTimeMillis() - d1);

        while (true) {
            try {
                if(inputQueue.size() == 0) {
                    flag = false;
                    break;
                }
                if(System.currentTimeMillis() - d1 > 2000L) {
                    Thread.sleep(1000L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (System.currentTimeMillis() - d1) + "ms," + dataCounter;
    }

    private HttpComponentsClientHttpRequestFactory getHttpComponentsClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(3 * 1000);
        httpRequestFactory.setConnectTimeout(2 * 1000);
        httpRequestFactory.setReadTimeout(2000);
        return httpRequestFactory;
    }

    private void writeTxtFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(FlowGenDataConstants.DATA_FILENAME),
                true))) {
            writer.write(content);
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doSend(DataCounter dataCounter, DataNode data) throws InterruptedException {
        DataNode dataNode = Objects.isNull(data) ? inputQueue.poll() : data;
        if(Objects.isNull(dataNode)) {
            return;
        }
        //当前时间
        long startTime = dataNode.getDate();
        while (true) {
            String uu = String.format(url + FlowGenDataConstants.API, dataNode.getDfp(), sysChannel);
            System.out.println(uu);
            ResponseEntity<String> resp = template.getForEntity(uu, String.class);
            if (resp.getBody().indexOf("\"code\":200") != -1) {
                System.out.println("done");
                writeTxtFile("success,"+ dataNode.getDfp() + "," + startTime + "," + (System.currentTimeMillis() - startTime));
                dataCounter.getSuccess().incrementAndGet();
                break;
            } else {
                if (System.currentTimeMillis() - startTime >= 10000) {
                    System.out.println("loop done");
                    writeTxtFile("fail,"+dataNode.getDfp() + "," + startTime + "," + (System.currentTimeMillis() - startTime));
                    dataCounter.getFail().incrementAndGet();
                    break;
                }
                System.out.println("loop");
                Thread.sleep(50);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        inputQueue = new LinkedBlockingQueue<>(100000);
        template = new RestTemplate();
        template.setRequestFactory(getHttpComponentsClientHttpRequestFactory());
        concurrentHandleRecordCount = 0;
        threadPool = Executors.newFixedThreadPool(1);
    }
}
