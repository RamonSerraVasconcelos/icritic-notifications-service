package com.icritic.notifications.core.usecase;

import com.icritic.notifications.config.properties.ApplicationProperties;
import com.icritic.notifications.core.model.Email;
import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.core.model.PasswordResetRequest;
import com.icritic.notifications.core.utils.ExternalNotificationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendPasswordResetRequestNotificationUseCase {

    private final SendEmailNotificationUseCase sendEmailNotificationUseCase;

    private final ApplicationProperties applicationProperties;

    public void execute(PasswordResetRequest passwordResetRequest) {
        try {
            ExternalNotification notification = ExternalNotificationUtils.buildNotification(applicationProperties.getKafkaPasswordResetRequestTopic(),
                    passwordResetRequest.getUserId(), passwordResetRequest.getEmail());

            String emailBody = "<h1>Password reset request</h1>" +
                    "<p>If you didn't request a password reset, ignore this email.</p>" +
                    "<a href=\"${front_end_link}/" + passwordResetRequest.getPasswordResetHash() + "\" target=\"_blank\">Click here</a> to reset your password!";

            Email email = Email.builder()
                    .from("no-reply@icritic.com")
                    .to(passwordResetRequest.getEmail())
                    .subject("Password Reset Request")
                    .body(emailBody)
                    .build();

            sendEmailNotificationUseCase.execute(email, notification);
        } catch (Exception e) {
            log.error("Error sending password reset request notification to email: [{}]. Error: [{}]", passwordResetRequest.getEmail(), e.getMessage());
        }
    }
}
