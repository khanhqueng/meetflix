package com.khanhisdev.payment_service.dto.request;

import com.khanhisdev.payment_service.constants.ZaloPayConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZaloPayCreateOrderRequest {
    private final int app_id= ZaloPayConfig.APP_ID;
    private String app_user= ZaloPayConfig.APP_USER;
    private String app_trans_id;
    private Long app_time;
    private Long amount;
    private List<Item> item;
    private String description;
    private String callback_url;
    private String embed_data;
    private String bank_code;
    private String mac;
    public void makeSignature( String key, String jsonItem) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String data= app_id +"|" +app_trans_id +"|"+app_user +"|"+amount +"|"+app_time +"|" +embed_data +"|" + jsonItem;
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        mac= Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes()));
    }
}
