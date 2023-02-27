package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;

/**
 * http连接属性类型
 *
 * @author lanhaifeng
 * @version 1.5.0
 * @apiNote 时间:2022/10/19 14:15创建:HttpClientProperties
 * @since 1.5.0
 */
@Getter
@Setter
public class HttpClientProperties {
    /**
     * 是否使用httpclient连接池
     */
    private boolean useHttpClientPool = false;
    /**
     * 从连接池中获得一个connection的超时时间
     */
    private int connectionRequestTimeout = 2000;
    /**
     * 建立连接超时时间
     */
    private int connectTimeout = 1000;
    /**
     * 建立连接后读取返回数据的超时时间
     */
    private int readTimeout = 3000;
    /**
     * 最大空闲时间
     */
    private int maxIdleTime = 5000;
    /**
     * 连接池的最大连接数，0代表不限
     */
    private int maxTotalConnect = 800;
    /**
     * 每个路由的最大连接数
     */
    private int maxConnectPerRoute = 400;
}
