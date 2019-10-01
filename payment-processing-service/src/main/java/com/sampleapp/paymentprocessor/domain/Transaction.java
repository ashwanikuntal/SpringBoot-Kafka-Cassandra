package com.sampleapp.paymentprocessor.domain;

import java.time.Instant;

public class Transaction {
	
	private String txnId;
	private String cardHolderName;
	private String cardNumber;
	private String pin;
	private String status;
	
	public Transaction() { }
	
	public Transaction(String txnId, String cardHolderName, String cardNumber, String pin, Instant txnTime,
			String status) {
		super();
		this.txnId = txnId;
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.pin = pin;
		this.status = status;
	}

	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Transaction [txnId=" + txnId + ", cardHolderName=" + cardHolderName + ", cardNumber=" + cardNumber
				+ ", pin=" + pin + ", status=" + status + "]";
	}

}
