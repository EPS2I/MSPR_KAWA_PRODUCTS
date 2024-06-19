package com.mspr_kawa.products.main.rabbitmq;

import com.mspr_kawa.products.main.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key.json}")
    private String routingKeyJson;
    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(Product product){
        LOGGER.info(String.format(" JSON Message sent ->: %s", product.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKeyJson, product);

    }
}
