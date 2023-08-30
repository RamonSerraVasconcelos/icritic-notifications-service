package com.icritic.notifications.core.usecase;

import com.icritic.notifications.config.properties.ApplicationProperties;
import com.icritic.notifications.core.model.Email;
import com.icritic.notifications.core.model.EmailResetRequest;
import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.core.utils.ExternalNotificationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SendEmailResetRequestNotificationUseCase {

    private final SendEmailNotificationUseCase sendEmailNotificationUseCase;

    private final ApplicationProperties applicationProperties;

    public void execute(EmailResetRequest emailResetRequest) {
        try {
            ExternalNotification notification = ExternalNotificationUtils.buildNotification(applicationProperties.getKafkaPasswordResetRequestTopic(),
                    emailResetRequest.getUserId(), emailResetRequest.getEmail());

            String emailBody = "<h1>Email reset request</h1>" +
                    "<p>If you didn't request an email reset, ignore this email.</p>" +
                    "<a href=\"${front_end_link}/" + emailResetRequest.getUserId() + "/" + emailResetRequest.getEmailResetHash() + "\" target=\"_blank\">Click here</a> to reset your email!";

            Email email = Email.builder()
                    .from("no-reply@icritic.com")
                    .to(emailResetRequest.getEmail())
                    .subject("Email Reset Request")
                    .body(emailBody)
                    .build();

            sendEmailNotificationUseCase.execute(email, notification);
        } catch (Exception e) {
            log.error("Error sending email reset request notification to email: [{}]. Error: [{}]", emailResetRequest.getEmail(), e.getMessage());
        }
    }
}
