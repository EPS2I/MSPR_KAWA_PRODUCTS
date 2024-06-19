package com.mspr_kawa.products.main.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    private final RabbitMQConfig rabbitMQConfig;

    public RabbitMQConsumer(RabbitMQConfig rabbitMQConfig) {
        this.rabbitMQConfig = rabbitMQConfig;
    }

    @RabbitListener(queues = "#{rabbitMQConfig.queue}")
    public void consume(String message) {
        LOGGER.info(String.format("Received Product Message -> %s", message));
        // Logique de traitement du message avec customerSyncService
    }
}