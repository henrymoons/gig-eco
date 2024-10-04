package com.moon.gigeco.service.payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.moon.gigeco.api.payment.dto.PaymentRequest;
import com.moon.gigeco.api.payment.dto.PaymentResponse;

@Service
public class TossPaymentService implements PaymentService {

    @Value("${toss.api.key}")
    private String tossApiKey;
    
    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(tossApiKey);

        // TossPayments 결제 요청 데이터
        Map<String, Object> paymentData = new HashMap<>();
        paymentData.put("amount", request.getAmount());
        paymentData.put("orderId", request.getOrderId());
        paymentData.put("orderName", request.getOrderName());
        paymentData.put("customerName", request.getCustomerName());
        paymentData.put("successUrl", request.getSuccessUrl());
        paymentData.put("failUrl", request.getFailUrl());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(paymentData, headers);
        
        try {
//            ResponseEntity<TossPaymentResponse> response = restTemplate.postForEntity(
//                "https://api.tosspayments.com/v1/payments", entity, TossPaymentResponse.class);
//            
//            // 결제 성공 처리
            return new PaymentResponse("SUCCESS", "결제가 완료되었습니다.");
        } catch (RestClientException e) {
//            // 결제 실패 처리
            return new PaymentResponse("FAIL", "결제 실패");
        }
    }
}

