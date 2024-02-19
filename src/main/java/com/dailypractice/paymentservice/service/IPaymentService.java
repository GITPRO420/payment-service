package com.dailypractice.paymentservice.service;

import com.dailypractice.paymentservice.model.PaymentRequest;
import com.dailypractice.paymentservice.model.PaymentResponse;

public interface IPaymentService {

	Long doPayment(PaymentRequest paymentRequest);

	PaymentResponse getPaymentDetailsByOrderId(Long orderId);

}
