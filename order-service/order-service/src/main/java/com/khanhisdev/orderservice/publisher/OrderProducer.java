package com.khanhisdev.orderservice.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.binding.routing.key}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public <V> void sendMessage( V object){
        rabbitTemplate.convertAndSend(exchange,routingKey,object);
    }
}
