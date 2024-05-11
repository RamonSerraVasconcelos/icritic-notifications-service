package com.icritic.notifications.core.utils;

import com.icritic.notifications.core.model.ExternalNotification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExternalNotificationUtils {
    public static ExternalNotification buildNotification(String notificationSubjectId, Long notifierId, String email) {
        return ExternalNotification.builder()
                .notificationSubjectId(notificationSubjectId)
                .notifierId(notifierId)
                .email(email)
                .build();
    }
}
