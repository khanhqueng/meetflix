package com.khanhisdev.payment_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.khanhisdev.payment_service.dto.request.ZaloPayCreateOrderRequest;
import com.khanhisdev.payment_service.dto.response.ZaloPayCreateOrderResponse;
import com.khanhisdev.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping("/zalo-pay")
    public ResponseEntity<ZaloPayCreateOrderResponse> createOrder (@RequestBody ZaloPayCreateOrderRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        return new ResponseEntity<>(paymentService.createOrder(request), HttpStatus.OK);
    }
}
