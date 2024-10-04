package com.moon.gigeco.service.payment;

import com.moon.gigeco.api.payment.dto.PaymentRequest;
import com.moon.gigeco.api.payment.dto.PaymentResponse;

public interface PaymentService {
	PaymentResponse processPayment(PaymentRequest request);
}
