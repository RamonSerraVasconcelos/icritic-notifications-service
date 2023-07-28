package com.icritic.notifications.dataprovider.kafka.consumer;

import com.icritic.notifications.dataprovider.kafka.entity.PasswordResetRequestMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PasswordResetRequestConsumer {

    @KafkaListener(topics = "${spring.application.properties.kafka-password-reset-request-topic}", groupId = "${spring.application.properties.kafka-group-id}")
    public void execute(PasswordResetRequestMessage passwordResetRequestMessage) {
        try {
            log.info("Received password reset request notification");
        } catch (Exception e) {
            log.error("Error receiving password reset request notification", e);
        }
    }
}
