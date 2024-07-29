package com.khanhisdev.emailservice.dto.message;

import lombok.Data;

@Data
public class OrderEvent {
    private String status;
    private String message;
    private EmailContent content;
}
