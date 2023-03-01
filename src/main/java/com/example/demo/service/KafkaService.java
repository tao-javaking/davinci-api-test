package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
* @author cgj
* 类说明
*/
@Service
public class KafkaService {
	@Resource
    private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${spring.kafka.producer.topic:dfp}")
	private String topic;

	public ListenableFuture<SendResult<String, String>> send(String json) {
		return kafkaTemplate.send(topic,json);
	}
}
