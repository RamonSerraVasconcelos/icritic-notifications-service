package com.icritic.notifications.core.utils;

import com.icritic.notifications.core.model.ExternalNotification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExternalNotificationUtils {
    public static ExternalNotification buildNotification(String topic, Long notifierId, String email) {
        return ExternalNotification.builder()
                .topic(topic)
                .notifierId(notifierId)
                .email(email)
                .build();
    }
}
