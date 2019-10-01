package com.sampleapp.paymentstarterservice.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.sampleapp.paymentstarterservice.domain.Transaction;

import reactor.core.publisher.Mono;

@Repository
public interface TransactionRepository extends ReactiveCassandraRepository<Transaction, Long>{

	//Mono<Transaction> save(Transaction txn);
}
