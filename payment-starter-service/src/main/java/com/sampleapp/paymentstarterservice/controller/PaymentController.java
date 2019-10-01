package com.sampleapp.paymentstarterservice.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sampleapp.paymentstarterservice.domain.Transaction;
import com.sampleapp.paymentstarterservice.repository.TransactionRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/payment")
@Api(value="Transactions", description="Services for posting transaction data")
public class PaymentController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@PostMapping(value="/enterDetails")
	@ApiOperation(value = "Post Payment Inputs", nickname="postPaymentInputs")
	public void enterCardDetails(@RequestParam(value="username") String cardHolderName, 
			@RequestParam(value="cardNumber") String cardNumber, @RequestParam(value="pin") String pin) {
		
		Transaction txn = new Transaction();
		txn.setCardHolderName(cardHolderName);
		txn.setCardNumber(cardNumber);
		txn.setPin(pin);
		txn.setTxnId(String.valueOf(Instant.now().toEpochMilli()));
		txn.setStatus("In Progress");

		transactionRepository.save(txn).subscribe(id -> System.out.println("Saved Abc with id: " + id));
		txn = this.restTemplate.postForObject("http://payment-processing-service/bank/authenticate", txn, Transaction.class);
		
	}
	
	

}
