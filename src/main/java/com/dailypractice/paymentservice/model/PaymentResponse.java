package com.dailypractice.paymentservice.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
	
	private Long paymentId;
	private String status;
	private PaymentMode paymentMode;
	private Long amount;
	private Instant paymentDate;
	private Long orderId;

}
