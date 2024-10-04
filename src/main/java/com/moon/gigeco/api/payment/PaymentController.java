package com.moon.gigeco.api.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moon.gigeco.api.payment.dto.PaymentRequest;
import com.moon.gigeco.api.payment.dto.PaymentResponse;
import com.moon.gigeco.service.payment.PaymentService;
import com.moon.gigeco.service.point.PointService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PointService pointService;

//    public PaymentController(PaymentService paymentService, PointService pointService) {
//        this.paymentService = paymentService;
//        this.pointService = pointService;
//    }

    @PostMapping("/charge")
    public ResponseEntity<String> chargePoints(@RequestBody PaymentRequest request) {
        PaymentResponse paymentResponse = paymentService.processPayment(request);

        if ("SUCCESS".equals(paymentResponse.getStatus())) {
            // 결제 성공 시 포인트 적립
            pointService.addPoints(request.getUserId(), request.getPoints());
            return ResponseEntity.ok("결제 및 포인트 충전 성공");
        } else {
            // 결제 실패 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("결제 실패");
        }
    }

    @GetMapping("/success")
    public String handleSuccess(@RequestParam String paymentKey, @RequestParam String orderId, @RequestParam String amount) {
        // 결제 성공 처리 후 리다이렉트
        return "결제 성공!";
    }

    @GetMapping("/fail")
    public String handleFail() {
        // 결제 실패 처리
        return "결제 실패!";
    }
}