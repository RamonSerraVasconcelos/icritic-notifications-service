package com.icritic.notifications.core.fixture;

import com.icritic.notifications.core.model.EmailNotificationRequest;

public class EmailNotificationRequestFixture {

    public static EmailNotificationRequest load() {
        return EmailNotificationRequest.builder()
                .notificationSubjectId("test")
                .userId(1L)
                .email("test@test.test")
                .subject("test")
                .body("test")
                .build();
    }
}
