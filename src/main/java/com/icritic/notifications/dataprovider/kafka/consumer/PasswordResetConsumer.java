package com.icritic.notifications.dataprovider.kafka.consumer;

import com.icritic.notifications.core.usecase.SendPasswordResetNotificationUseCase;
import com.icritic.notifications.dataprovider.kafka.entity.PasswordResetMessage;
import com.icritic.notifications.dataprovider.kafka.mapper.PasswordResetMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PasswordResetConsumer {

    private final SendPasswordResetNotificationUseCase sendPasswordResetNotificationUseCase;

    @KafkaListener(topics = "${spring.application.properties.kafka-password-reset-topic}", groupId = "${spring.application.properties.kafka-group-id}", containerFactory="kafkaPasswordResetListenerContainerFactory")
    public void execute(PasswordResetMessage passwordResetMessage) {
        try {
            log.info("Received password reset message");

            PasswordResetMessageMapper mapper = PasswordResetMessageMapper.INSTANCE;

            sendPasswordResetNotificationUseCase.execute(mapper.passwordResetMessageToPasswordReset(passwordResetMessage));
        } catch (Exception e) {
            log.error("Error receiving password reset notification", e);
        }
    }
}
