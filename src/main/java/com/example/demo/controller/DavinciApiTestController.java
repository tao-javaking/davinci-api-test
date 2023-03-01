package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.FlowGenDataConstants;
import com.example.demo.domain.DataCounter;
import com.example.demo.domain.DataNode;
import com.example.demo.service.KafkaService;
import com.example.demo.utils.PlatformDataUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
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
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.demo.constant.FlowGenDataConstants.*;

/**
 * @author cgj 类说明
 */
@RestController
@RequestMapping("/davinci/api")
@Slf4j
public class DavinciApiTestController implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(DavinciApiTestController.class);
    @Value("${flow.url:http://10.100.1.169}")
    private String url;
    @Value("${flow.sys-channel:test1}")
    private String sysChannel;
    @Value("${flow.send-config.wait-time:1000}")
    private final long timeout = 1000L;
    @Value("${flow.send-config.batch-size:2000}")
    private final int batchSize = 2000;
    @Value("${flow.send-config.queue-count:10}")
    private int concurrentQueueCount;
    @Resource
    private KafkaService kafkaService;
    private ConcurrentLinkedDeque<DataNode> inputQueue;
    @Resource
    private RestTemplate template;
    private boolean flag;
    private ExecutorService threadPool;

    @GetMapping("/get/{limit}")
    public String send(@PathVariable int limit) throws InterruptedException {
        if(flag) {
            return REQUEST_NOT_END;
        }
        long startTime = System.currentTimeMillis();
        long d1 = System.currentTimeMillis();
        flag = true;
        DataCounter dataCounter = new DataCounter();
        dataCounter.getAll().set(limit);

        int doTime = limit % batchSize == 0 ? limit / batchSize : limit / batchSize + 1;
        for (int i = 0; i < doTime; i++) {
            int realSize = batchSize;
            if(i == doTime - 1) {
                realSize = limit - batchSize * i;
            }
            doTask(realSize, dataCounter);
        }

        for (int j = 0; j < concurrentQueueCount; j++) {
            while (!returnMethod(dataCounter, dataCounter.getAll().get())) {
                doSend(dataCounter);
            }
        }

        while (true) {
            try {
                if(dataCounter.getAll().get() <= dataCounter.getSuccess().get() + dataCounter.getFail().get()) {
                    flag = false;
                    break;
                }
                if(System.currentTimeMillis() - d1 > 2000L) {
                    Thread.sleep(1000L);
                    logger.info(dataCounter.toString());
                    d1 = System.currentTimeMillis();
                }
            } catch (Exception e) {
                logger.error(WAIT_EXCUTE_FAILED, e);
            }
        }

        String result = (System.currentTimeMillis() - startTime) + "ms," + dataCounter;
        logger.info(result);
        return result;
    }

    private void writeTxtFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(FlowGenDataConstants.DATA_FILENAME),
                true))) {
            writer.write(content);
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            logger.error(FILE_WRITE_FAILED, e);
        }
    }

    private synchronized boolean check(AtomicInteger count, int limit) {
        return count.get() < limit;
    }

    private boolean returnMethod (@NonNull DataCounter dataCounter, long limit) {
        synchronized (dataCounter) {
            return limit <= dataCounter.getSuccess().get() + dataCounter.getFail().get();
        }
    }

    private void doTask(int batchSize, DataCounter dataCounter) {
        AtomicInteger count = new AtomicInteger();
        while (check(count, batchSize)) {
            JSONObject dfpObj = new JSONObject();
            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> map = PlatformDataUtils.AND_MAP;
            String dfp = UUID.randomUUID().toString().replaceAll("-", "");
            map.put(FlowGenDataConstants.DFP, dfp);
            map.put(FlowGenDataConstants.OUT_CODE, UUID.randomUUID() + UUID.randomUUID().toString());
            list.add(map);
            dfpObj.put(FlowGenDataConstants.DFP_FLOWS, list);
            ListenableFuture<SendResult<String, String>> listenableFuture = kafkaService.send(dfpObj.toJSONString());
            count.incrementAndGet();
            listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    logger.error(KAFKA_SEND_MESSAGE_FAILED, throwable);
                }

                @Override
                public void onSuccess(SendResult<String, String> stringStringSendResult) {
                    //inputQueue.offer(new DataNode(System.currentTimeMillis(), dfp, dfpObj));
                    threadPool.submit(() -> {
                        DataNode dataNode = new DataNode(System.currentTimeMillis(), dfp, dfpObj);
                        if(doSend(dataCounter, dataNode) == 0) {
                            inputQueue.offer(dataNode);
                        }
                    });
                }
            });
        }
        while (returnMethod(dataCounter, batchSize)) {
            return;
        }
    }

    public int doSend(@NonNull DataCounter dataCounter, @NonNull DataNode dataNode) {
        //开始时间
        long startTime = dataNode.getDate();
        String uu = String.format(url + FlowGenDataConstants.API, dataNode.getDfp(), sysChannel);
        int status = 0;
        String messageTemplate = RESULT_WRITE_TEMPLATE;

        if (System.currentTimeMillis() - startTime >= timeout) {
            writeTxtFile(String.format(messageTemplate, "fail", uu, dataNode.getDfp(), startTime, System.currentTimeMillis() - startTime));
            dataCounter.getFail().incrementAndGet();
            status = 2;
        }

        ResponseEntity<String> resp = template.getForEntity(uu, String.class);
        if (Optional.ofNullable(resp.getBody()).orElse("").contains(RESPONSE_TEMPLATE)) {
            writeTxtFile(String.format(messageTemplate, "success", uu, dataNode.getDfp(), startTime, System.currentTimeMillis() - startTime));
            dataCounter.getSuccess().incrementAndGet();
            status = 1;
        }

        return status;
    }

    public void doSend(DataCounter dataCounter) {
        DataNode dataNode = inputQueue.poll();
        if(Objects.isNull(dataNode)) {
            return;
        }
        if(doSend(dataCounter, dataNode) == 0) {
            inputQueue.offer(dataNode);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        inputQueue = new ConcurrentLinkedDeque<>();
        threadPool = Executors.newFixedThreadPool(200);
    }
}
