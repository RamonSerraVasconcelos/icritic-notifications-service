package com.icritic.notifications.core.usecase;

import com.icritic.notifications.core.model.Email;
import com.icritic.notifications.core.model.EmailNotificationRequest;
import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.core.usecase.boundary.SendEmailBoundary;
import com.icritic.notifications.core.utils.ExternalNotificationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendEmailNotificationUseCase {

    private final SaveExternalNotificationUseCase saveNotificationUseCase;

    private final SendEmailBoundary sendEmailBoundary;

    public void execute(EmailNotificationRequest emailNotificationRequest) throws Exception {
        ExternalNotification notification = ExternalNotificationUtils.buildNotification(emailNotificationRequest.getNotificationSubjectId(),
                emailNotificationRequest.getUserId(), emailNotificationRequest.getEmail());

        try {
            log.info("Sending email notification with subjectId: [{}] to: [{}]", emailNotificationRequest.getNotificationSubjectId(), emailNotificationRequest.getEmail());

            Email email = Email.builder()
                    .from("no-reply@icritic.com")
                    .to(emailNotificationRequest.getEmail())
                    .subject(emailNotificationRequest.getSubject())
                    .body(emailNotificationRequest.getBody())
                    .build();

            sendEmailBoundary.execute(email);

            notification.setSent(true);
        } catch (Exception e) {
            notification.setSent(false);
            log.error("Error sending email notification to: {}", emailNotificationRequest.getEmail(), e);
            throw e;
        } finally {
            saveNotificationUseCase.execute(notification);
        }
    }
}
