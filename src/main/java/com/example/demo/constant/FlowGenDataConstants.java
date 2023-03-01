package com.example.demo.constant;

public final class FlowGenDataConstants {

    private FlowGenDataConstants() {
    }

    /**
     * 画像接口调用API后缀
     */
    public static final String API = "/davinci/apiMgr/device/%s?sysChannel=%s";
    /**
     * 流水前缀
     */
    public static final String DFP_FLOWS = "dfpFlows";
    /**
     * 指纹
     */
    public static final String DFP = "dfp";
    /**
     * 外码
     */
    public static final String OUT_CODE = "outCode";
    /**
     * 指纹数据输出文件名
     */
    public static final String DATA_FILENAME = "data.txt";
    /**
     * 调用记录模板
     */
    public static final String RESULT_WRITE_TEMPLATE = "result:%s,url:%s,%s,%s,%s";
    /**
     * 调用记录模板
     */
    public static final String RESPONSE_TEMPLATE = "\"code\":200";



    /**
     * 异常信息文本
     */
    public static final String REQUEST_NOT_END = "请等上次测试结果结束，再发送压测请求";
    public static final String WAIT_EXCUTE_FAILED = "等待执行完毕出错：";
    public static final String FILE_WRITE_FAILED = "写文件异常：";
    public static final String KAFKA_SEND_MESSAGE_FAILED = "kafka send message fail,error:";
}
