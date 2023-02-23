package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author cgj
* 类说明
*/
@Service
public class KafkaService {
	@Resource
    private KafkaTemplate<String,Object> kafkaTemplate;

	@Value("${dfp.topic:dfp}")
	private String topic;

	public void send(String json) {
		kafkaTemplate.send(topic,json);
	}
}
