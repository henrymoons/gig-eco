package com.moon.gigeco.api.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentResponse {
	private String status; // 결제 상태 ("SUCCESS", "FAIL")
	private String message;
}
