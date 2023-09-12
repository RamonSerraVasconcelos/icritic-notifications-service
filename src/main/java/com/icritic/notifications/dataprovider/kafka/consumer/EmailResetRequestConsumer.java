package com.icritic.notifications.dataprovider.kafka.consumer;

import com.icritic.notifications.core.usecase.SendEmailResetRequestNotificationUseCase;
import com.icritic.notifications.dataprovider.kafka.entity.EmailResetRequestMessage;
import com.icritic.notifications.dataprovider.kafka.mapper.EmailResetRequestMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailResetRequestConsumer {

    private final SendEmailResetRequestNotificationUseCase sendEmailResetRequestNotificationUseCase;

    @KafkaListener(topics = "${spring.application.properties.kafka-email-reset-request-topic}", groupId = "${spring.application.properties.kafka-group-id}", containerFactory = "emailResetRequestListenerContainerFactory")
    public void execute(EmailResetRequestMessage emailResetRequestMessage) {
        try {
            log.info("Received email reset request message");

            EmailResetRequestMessageMapper mapper = EmailResetRequestMessageMapper.INSTANCE;

            sendEmailResetRequestNotificationUseCase.execute(mapper.emailResetRequestMessageToemailResetRequest(emailResetRequestMessage));
        } catch (Exception e) {
            log.error("Error receiving email reset request notification", e);
        }
    }
}
