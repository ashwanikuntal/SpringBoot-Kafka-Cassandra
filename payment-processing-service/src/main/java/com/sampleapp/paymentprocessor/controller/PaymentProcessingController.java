package com.sampleapp.paymentprocessor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sampleapp.paymentprocessor.domain.Transaction;
import com.sampleapp.paymentprocessor.service.KafkaSender;

@RestController
@RequestMapping(value="/bank")
public class PaymentProcessingController {
	
	@Autowired
	KafkaSender kafkaSender;
	
	@PostMapping(value = "/authenticate")
	public void authenticatePayment(@RequestBody Transaction txnObj) {
		if (txnObj.getCardHolderName().equals("ashwani") && txnObj.getCardNumber().equals("12345") && txnObj.getPin().equals("123")) {
			Transaction txn = new Transaction();
			txn.setTxnId(txnObj.getTxnId());
			txn.setCardHolderName(txnObj.getCardHolderName());
			txn.setCardNumber(txnObj.getCardNumber());
			txn.setPin(txnObj.getPin());
			txn.setStatus("Success");
			String message = producer(txn);
			System.out.println(message);
		}
	}

	public String producer(Transaction txn) {
		kafkaSender.send(txn);
		return txn.toString();
	}

}
