package com.khanhisdev.payment_service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khanhisdev.payment_service.constants.ZaloPayConfig;
import com.khanhisdev.payment_service.dto.request.ZaloPayCreateOrderRequest;
import com.khanhisdev.payment_service.dto.response.ZaloPayCreateOrderResponse;
import com.khanhisdev.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private WebClient webClient;
    @Override
    public ZaloPayCreateOrderResponse createOrder(ZaloPayCreateOrderRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        request.setApp_time(System.currentTimeMillis());
        request.setApp_trans_id(LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"))+ "_"+ request.getApp_time());
        ObjectMapper objectMapper = new ObjectMapper();
        String itemJson = objectMapper.writeValueAsString(request.getItem());
        request.makeSignature(ZaloPayConfig.KEY1, itemJson);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("app_id", String.valueOf(ZaloPayConfig.APP_ID));
        formData.add("app_trans_id", request.getApp_trans_id());
        formData.add("app_user", request.getApp_user());
        formData.add("amount", String.valueOf(request.getAmount()));
        formData.add("app_time", String.valueOf(request.getApp_time()));
        formData.add("description", request.getDescription());
        formData.add("bank_code", request.getBank_code());
        formData.add("embed_data", request.getEmbed_data());
        formData.add("item", itemJson);
        formData.add("callback_url", request.getCallback_url());
        formData.add("mac", request.getMac());

        ZaloPayCreateOrderResponse response = webClient.post()
                .uri(ZaloPayConfig.PAYMENT_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(ZaloPayCreateOrderResponse.class)
                .block();
        return response;
    }
}
