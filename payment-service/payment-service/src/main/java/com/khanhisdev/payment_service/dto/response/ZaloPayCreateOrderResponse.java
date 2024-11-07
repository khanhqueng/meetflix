package com.khanhisdev.payment_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZaloPayCreateOrderResponse {
    private int return_code;
    private String return_message;
    private int sub_return_code;
    private String sub_return_message;
    private String order_url;
    private String zp_trans_token;
    private String order_token;
    private String qr_code;
}
