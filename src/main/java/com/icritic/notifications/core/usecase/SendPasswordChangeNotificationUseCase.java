package com.icritic.notifications.core.usecase;

import com.icritic.notifications.config.properties.ApplicationProperties;
import com.icritic.notifications.core.model.Email;
import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.core.model.PasswordChange;
import com.icritic.notifications.core.utils.ExternalNotificationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SendPasswordChangeNotificationUseCase {

    private final SendEmailNotificationUseCase sendEmailNotificationUseCase;

    private final ApplicationProperties applicationProperties;

    public void execute(PasswordChange passwordChange) {
        try {
            ExternalNotification notification = ExternalNotificationUtils.buildNotification(applicationProperties.getKafkaPasswordChangeTopic(),
                    passwordChange.getUserId(), passwordChange.getEmail());

            String emailBody = "<h1>Password change notification</h1>" +
                    "<p>Your password was changed. If you didnt request this change please contact us.</p>";

            Email email = Email.builder()
                    .from("no-reply@icritic.com")
                    .to(passwordChange.getEmail())
                    .subject("Password Change Notification")
                    .body(emailBody)
                    .build();

            sendEmailNotificationUseCase.execute(email, notification);
        } catch (Exception e) {
            log.error("Error sending password change notification to email: [{}]. Error: [{}]", passwordChange.getEmail(), e.getMessage());
        }
    }
}
