package com.example.demo.config;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 配置RestTemplate
 *
 * @author lanhaifeng
 * @version 1.5.0
 * @apiNote 时间:2022/10/19 14:13创建:RestTemplateConfig
 * @since 1.5.0
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> c : messageConverters) {
            if (c instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) c).setDefaultCharset(StandardCharsets.UTF_8);
            }
        }
        return restTemplate;
    }

    @Bean
    @ConfigurationProperties(prefix = "api-test.http-client")
    HttpClientProperties httpClientProperties() {
        return new HttpClientProperties();
    }

    @Bean
    ClientHttpRequestFactory clientHttpRequestFactory(HttpClientProperties httpClientProperties) {
        //如果不使用HttpClient的连接池，则使用restTemplate默认的SimpleClientHttpRequestFactory,底层基于HttpURLConnection
        if (!httpClientProperties.isUseHttpClientPool()) {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(httpClientProperties.getConnectTimeout());
            factory.setReadTimeout(httpClientProperties.getReadTimeout());
            return factory;
        }
        //HttpClient4.3及以上版本不手动设置HttpClientConnectionManager,默认就会使用连接池PoolingHttpClientConnectionManager
        HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(httpClientProperties.getMaxTotalConnect())
                .setMaxConnPerRoute(httpClientProperties.getMaxConnectPerRoute()).evictExpiredConnections()
                .evictIdleConnections(httpClientProperties.getMaxIdleTime(), TimeUnit.MILLISECONDS)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultHeaders(getDefaultHeaders())
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setConnectTimeout(httpClientProperties.getConnectTimeout());
        factory.setReadTimeout(httpClientProperties.getReadTimeout());
        factory.setConnectionRequestTimeout(httpClientProperties.getConnectionRequestTimeout());
        return factory;
    }

    private List<Header> getDefaultHeaders() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));
        return headers;
    }
}
