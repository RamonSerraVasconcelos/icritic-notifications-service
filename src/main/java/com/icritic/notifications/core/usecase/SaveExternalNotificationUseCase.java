package com.icritic.notifications.core.usecase;

import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.core.usecase.boundary.SaveExternalNotificationBoundary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveExternalNotificationUseCase {

    private final SaveExternalNotificationBoundary saveNotificationBoundary;

    public void execute(ExternalNotification notification) {
        try {
            notification.setNotificationId(UUID.randomUUID().toString());
            notification.setCreatedAt(LocalDateTime.now());

            log.info("Saving external notification with id: [{}]", notification.getNotificationId());

            saveNotificationBoundary.execute(notification);
        } catch (Exception e) {
            log.error("Error saving external notification with id: [{}]", notification.getNotificationId(),  e);
        }
    }
}
