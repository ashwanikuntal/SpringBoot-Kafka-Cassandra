package com.sampleapp.paymentprocessor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sampleapp.paymentprocessor.domain.Transaction;

@Service
public class KafkaSender {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	String kafkaTopic = "testtopic"; String message = null;
	
	public void send(Transaction txn) {
	    ObjectMapper mapper = new ObjectMapper();
	    try {
			message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(txn);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	    kafkaTemplate.send(kafkaTopic, message);
	}
	
}
