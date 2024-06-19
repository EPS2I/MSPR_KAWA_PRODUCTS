package com.mspr_kawa.products.main.rabbitmq;

import com.mspr_kawa.products.main.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class RabbitMQJsonController {
    private final RabbitMQJsonProducer jsonProducer;
    public RabbitMQJsonController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("/publish/json")
    public ResponseEntity<String> sendJsonMessage(@RequestBody Product product){
        jsonProducer.sendJsonMessage(product);
        return ResponseEntity.ok(" Json  Product Message sent to RabbitMQ ....");
    }
}
