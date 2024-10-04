package com.moon.gigeco.api.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
	private Long userId;          // 사용자 ID
	private String orderId;       // 주문 ID
	private String orderName;     // 포인트 충전
	private int amount;           // 결제 금액
	private String customerName;  // 사용자 이름
	private String successUrl;    // 결제 성공 시 리다이렉트될 URL
	private String failUrl;       // 결제 실패 시 리다이렉트될 URL
	private int points;           // 충전할 포인트
}
