package com.icritic.notifications.core.fixture;

import com.icritic.notifications.core.model.ExternalNotification;

import java.time.LocalDateTime;
import java.util.UUID;

public class ExternalNotificationFixture {

    public static ExternalNotification load() {
        return ExternalNotification.builder()
                .notificationId(UUID.randomUUID().toString())
                .notificationSubjectId("test")
                .notifierId(1L)
                .email("test@test.test")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
