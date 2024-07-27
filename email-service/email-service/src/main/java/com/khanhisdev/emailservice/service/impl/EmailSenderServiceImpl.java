package com.khanhisdev.emailservice.service.impl;

import com.khanhisdev.emailservice.service.EmailSenderService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {
    private Configuration freemakerConfiguration;
    private JavaMailSender javaMailSender;


    @Override
    public void sendSeatsInformation(String toEmail, Map<String, Object> attributes) {
        String text =getEmailContent("seats-info.ftlh", attributes);
        sendEmail(toEmail, "[Betflix Cinema] Bạn đặt vé thành công", text);
    }

    private void sendEmail(String toEmail, String subject, String text) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getEmailContent(String template, Map<String, Object> attributes) {
        try {
            StringWriter stringWriter = new StringWriter();
            freemakerConfiguration.getTemplate(template).process(attributes, stringWriter);
            return stringWriter.getBuffer().toString();
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
