package com.icritic.notifications.core.usecase;

import com.icritic.notifications.config.properties.ApplicationProperties;
import com.icritic.notifications.core.model.Email;
import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.core.model.PasswordReset;
import com.icritic.notifications.core.utils.ExternalNotificationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SendPasswordResetNotificationUseCase {

    private final SendEmailNotificationUseCase sendEmailNotificationUseCase;

    private final ApplicationProperties applicationProperties;

    public void execute(PasswordReset passwordReset) {
        try {
            ExternalNotification notification = ExternalNotificationUtils.buildNotification(applicationProperties.getKafkaPasswordResetRequestTopic(),
                    passwordReset.getUserId(), passwordReset.getEmail());

            String emailBody = "<h1>Password reset notification</h1>" +
                    "<p>Your password was successfully reset on iCritic.</p>";

            Email email = Email.builder()
                    .from("no-reply@icritic.com")
                    .to(passwordReset.getEmail())
                    .subject("Password Reset Notification")
                    .body(emailBody)
                    .build();

            sendEmailNotificationUseCase.execute(email, notification);
        } catch (Exception e) {
            log.error("Error sending password reset notification to email: [{}]. Error: [{}]", passwordReset.getEmail(), e.getMessage());
        }
    }
}
