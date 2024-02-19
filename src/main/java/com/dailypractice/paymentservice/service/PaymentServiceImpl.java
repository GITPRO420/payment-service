package com.dailypractice.paymentservice.service;

import java.time.Instant;

import org.bouncycastle.crypto.engines.ISAACEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailypractice.paymentservice.entity.TransactionDetails;
import com.dailypractice.paymentservice.model.PaymentMode;
import com.dailypractice.paymentservice.model.PaymentRequest;
import com.dailypractice.paymentservice.model.PaymentResponse;
import com.dailypractice.paymentservice.repository.ITransactionDetailsRepo;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private ITransactionDetailsRepo transactionRepo;

	@Override
	public Long doPayment(PaymentRequest paymentRequest) {

		log.info("Recording Payment details{}", paymentRequest);

		TransactionDetails transactionDetails = TransactionDetails.builder().paymentDate(Instant.now())
				.paymentMode(paymentRequest.getPaymentMode().name()).paymentStatus("SUCCESS")
				.orderId(paymentRequest.getOrderId()).referenceNumber(paymentRequest.getReferenceNumber())
				.amount(paymentRequest.getAmount()).build();
		transactionRepo.save(transactionDetails);

		log.info("Transaction completed... with id{}", transactionDetails.getTransactionId());

		return transactionDetails.getTransactionId();
	}

	@Override
	public PaymentResponse getPaymentDetailsByOrderId(Long orderId) {
		log.info("Getting Payment Details for the Order Id::{}",orderId);
		TransactionDetails transactionDetails=transactionRepo.findByOrderId(orderId);
		PaymentResponse paymentResponse=PaymentResponse.builder()
				.paymentId(transactionDetails.getTransactionId())
				.paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
				.paymentDate(transactionDetails.getPaymentDate())
				.orderId(transactionDetails.getOrderId())
				.status(transactionDetails.getPaymentStatus())
				.amount(transactionDetails.getAmount())
				.build();
		return paymentResponse;
	}

}
