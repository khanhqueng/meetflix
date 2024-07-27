package com.khanhisdev.emailservice.service;

import java.util.Map;

public interface EmailSenderService {
    void sendSeatsInformation(String toEmail, Map<String,Object> attributes);
}
