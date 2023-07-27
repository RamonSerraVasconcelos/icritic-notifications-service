package com.icritic.notifications.dataprovider.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "externalNotification")
public class ExternalNotificationEntity {

    @Id
    private String notificationId;
    private String topic;
    private Long notifierId;
    private String email;
    private boolean sent;
    private LocalDateTime createdAt;
}
