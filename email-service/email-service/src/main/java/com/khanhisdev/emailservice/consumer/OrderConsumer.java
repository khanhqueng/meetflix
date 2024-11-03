package com.khanhisdev.emailservice.consumer;

import com.khanhisdev.emailservice.dto.message.OrderEvent;
import com.khanhisdev.emailservice.service.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderConsumer {
    @Autowired
    private EmailSenderService emailSenderService;

    private final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.order.name}")
    public void consume(OrderEvent event){
        LOGGER.info(String.format("Message has been received %s", event.toString()));
        String infoSeats= String.join(",",event.getContent().getSeats());
        Map<String ,Object> attributes= Map.of(
                "seat", infoSeats,
                "movieName", event.getContent().getNameMovie(),
                "theaterName", event.getContent().getNameTheater(),
                "roomName", event.getContent().getNameRoom(),
                "showtime", event.getContent().getShowtime()
        );
        emailSenderService.sendSeatsInformation(event.getContent().getDesEmail(), attributes);
        LOGGER.info("Email has been sent successfully");
    }
}
