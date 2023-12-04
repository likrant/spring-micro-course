package com.li.micro.course.notificationservice;

import com.li.micro.course.notificationservice.event.OrderPlaceEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class NotificationServiceApplication {

    private static final Logger log = LogManager.getLogger();
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderPlaceEvent orderPlaceEvent) {
        log.info("Received OrderPlace Notification: " + orderPlaceEvent.getOrderNumber());
    }

}
