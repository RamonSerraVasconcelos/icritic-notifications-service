package com.icritic.notifications.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExternalNotification {

    private String notificationId;
    private String topic;
    Long notifierId;
    String email;
    boolean sent;
    LocalDateTime createdAt;
}
