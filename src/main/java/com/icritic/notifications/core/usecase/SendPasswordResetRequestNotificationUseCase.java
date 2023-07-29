package com.icritic.notifications.core.usecase;

import com.icritic.notifications.config.properties.ApplicationProperties;
import com.icritic.notifications.core.model.Email;
import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.core.model.PasswordResetRequest;
import com.icritic.notifications.core.usecase.boundary.CreateExternalNotificationBoundary;
import com.icritic.notifications.core.usecase.boundary.SendEmailBoundary;
import com.icritic.notifications.core.usecase.boundary.UpdateExternalNotificationBoundary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class SendPasswordResetRequestNotificationUseCase {

    private final CreateExternalNotificationBoundary saveNotificationBoundary;

    private final UpdateExternalNotificationBoundary updateNotificationBoundary;

    private final SendEmailBoundary sendEmailBoundary;

    private final ApplicationProperties applicationProperties;

    public void execute(PasswordResetRequest passwordResetRequest) {
        try {
            log.info("Sending password reset request notification to email: {}", passwordResetRequest.getEmail());

            ExternalNotification notification = ExternalNotification.builder()
                    .notificationId(UUID.randomUUID().toString())
                    .topic(applicationProperties.getKafkaPasswordResetRequestTopic())
                    .notifierId(passwordResetRequest.getUserId())
                    .email(passwordResetRequest.getEmail())
                    .sent(false)
                    .createdAt(LocalDateTime.now())
                    .build();

            saveNotificationBoundary.execute(notification);

            String emailBody = "<h1>Password reset request</h1>" +
                    "<p>We received a email change request for your account</p>" +
                    "<a href=${link} target=\"_blank\">Click here</a> if you didn't make this request!";

            Email email = Email.builder()
                    .from("no-reply@icritic.com")
                    .to(passwordResetRequest.getEmail())
                    .subject("Password Reset Request")
                    .body(emailBody)
                    .build();

            sendEmailBoundary.execute(email);
            updateNotificationBoundary.execute(notification.getNotificationId());
        } catch (Exception e) {
            log.error("Error sending password reset request notification to email: {}", passwordResetRequest.getEmail(), e);
        }
    }
}
