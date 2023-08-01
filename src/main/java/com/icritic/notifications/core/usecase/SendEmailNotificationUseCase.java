package com.icritic.notifications.core.usecase;

import com.icritic.notifications.core.model.Email;
import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.core.usecase.boundary.SendEmailBoundary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendEmailNotificationUseCase {

    private final SaveExternalNotificationUseCase saveNotificationUseCase;

    private final SendEmailBoundary sendEmailBoundary;

    public void execute(Email email, ExternalNotification notification) throws Exception {
        try {
            log.info("Sending email notification to: {}", email.getTo());

            sendEmailBoundary.execute(email);

            notification.setSent(true);
        } catch (Exception e) {
            notification.setSent(false);
            log.error("Error sending email notification to: {}", email.getTo(), e);
            throw e;
        } finally {
            saveNotificationUseCase.execute(notification);
        }
    }
}
