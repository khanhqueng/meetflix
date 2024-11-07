package com.khanhisdev.payment_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.khanhisdev.payment_service.dto.request.ZaloPayCreateOrderRequest;
import com.khanhisdev.payment_service.dto.response.ZaloPayCreateOrderResponse;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface PaymentService {
    ZaloPayCreateOrderResponse createOrder(ZaloPayCreateOrderRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException;
}
