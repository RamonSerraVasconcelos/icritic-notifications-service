package com.icritic.notifications.dataprovider.kafka.consumer;

import com.icritic.notifications.core.usecase.SendPasswordResetRequestNotificationUseCase;
import com.icritic.notifications.dataprovider.kafka.entity.PasswordResetRequestMessage;
import com.icritic.notifications.dataprovider.kafka.mapper.PasswordResetRequestMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PasswordResetRequestConsumer {

    private final SendPasswordResetRequestNotificationUseCase sendRequestUseCase;

    @KafkaListener(topics = "${spring.application.properties.kafka-password-reset-request-topic}", groupId = "${spring.application.properties.kafka-group-id}")
    public void execute(PasswordResetRequestMessage passwordResetRequestMessage) {
        try {
            log.info("Received password reset request message");

            PasswordResetRequestMessageMapper mapper = PasswordResetRequestMessageMapper.INSTANCE;

            sendRequestUseCase.execute(mapper.passwordResetRequestMessageToPasswordResetRequest(passwordResetRequestMessage));
        } catch (Exception e) {
            log.error("Error receiving password reset request notification", e);
        }
    }
}
