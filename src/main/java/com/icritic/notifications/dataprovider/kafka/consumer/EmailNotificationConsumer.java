package com.icritic.notifications.dataprovider.kafka.consumer;

import com.icritic.notifications.core.model.EmailNotificationRequest;
import com.icritic.notifications.core.usecase.SendEmailNotificationUseCase;
import com.icritic.notifications.dataprovider.kafka.entity.EmailNotificationRequestMessage;
import com.icritic.notifications.dataprovider.kafka.mapper.EmailNotificationRequestMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailNotificationConsumer {

    private final SendEmailNotificationUseCase sendEmailNotificationUseCase;

    @KafkaListener(topics = "${spring.application.properties.kafka-email-notification-topic}", groupId = "${spring.application.properties.kafka-group-id}", containerFactory = "emailNotificationRequestListenerContainerFactory")
    public void execute(EmailNotificationRequestMessage emailNotificationRequestMessage) {
        try {
            log.info("Received email notification message");

            EmailNotificationRequestMessageMapper mapper = EmailNotificationRequestMessageMapper.INSTANCE;

            EmailNotificationRequest emailNotificationRequest = mapper.messageToModel(emailNotificationRequestMessage);

            sendEmailNotificationUseCase.execute(emailNotificationRequest);
        } catch (Exception e) {
            log.error("Error receiving email notification message", e);
        }
    }
}
