package com.sampleapp.paymentstarterservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sampleapp.paymentstarterservice.domain.Transaction;
import com.sampleapp.paymentstarterservice.repository.TransactionRepository;


@Service
public class KafkaReceiver {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@KafkaListener(topics = "testtopic", groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(Transaction txn) {
        System.out.println("Consumed JSON Message: " + txn.toString());
		txn.setStatus(txn.getStatus());
		transactionRepository.save(txn).subscribe(id -> System.out.println("Saved Abc with id: " + id));
    }

}
