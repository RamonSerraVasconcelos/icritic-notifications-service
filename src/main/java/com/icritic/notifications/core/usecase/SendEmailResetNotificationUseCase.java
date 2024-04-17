package com.icritic.notifications.core.usecase;

import com.icritic.notifications.config.properties.ApplicationProperties;
import com.icritic.notifications.core.model.Email;
import com.icritic.notifications.core.model.EmailReset;
import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.core.utils.ExternalNotificationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendEmailResetNotificationUseCase {

    private final SendEmailNotificationUseCase sendEmailNotificationUseCase;

    private final ApplicationProperties applicationProperties;

    public void execute(EmailReset emailReset) {
        try {
            ExternalNotification notification = ExternalNotificationUtils.buildNotification(applicationProperties.getKafkaEmailResetTopic(),
                    emailReset.getUserId(), emailReset.getEmail());

            String emailBody = "<h1>Email reset notification</h1>" +
                    "<h2>Your email has been reset, if you did do not recognize this operation, please contact us.</h2>";

            Email email = Email.builder()
                    .from("no-reply@icritic.com")
                    .to(emailReset.getEmail())
                    .subject("Email Reset Notification")
                    .body(emailBody)
                    .build();

            sendEmailNotificationUseCase.execute(email, notification);
        } catch (Exception e) {
            log.error("Error sending email reset notification to email: [{}]. Error: [{}]", emailReset.getEmail(), e.getMessage());
        }
    }
}
