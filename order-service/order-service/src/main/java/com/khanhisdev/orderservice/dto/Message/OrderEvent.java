package com.khanhisdev.orderservice.dto.Message;

import com.khanhisdev.orderservice.dto.Response.ShowtimeForOrderDto;
import lombok.Data;

@Data
public class OrderEvent {
    private String status;
    private String message;
    private EmailContent content;
}
