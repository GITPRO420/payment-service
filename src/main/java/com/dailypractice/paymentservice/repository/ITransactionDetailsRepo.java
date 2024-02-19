package com.dailypractice.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dailypractice.paymentservice.entity.TransactionDetails;

public interface ITransactionDetailsRepo extends JpaRepository<TransactionDetails, Long> {

	@Query("SELECT t FROM TransactionDetails t WHERE t.orderId = :orderId")
	TransactionDetails findByOrderId(@Param("orderId")Long orderId);

}
