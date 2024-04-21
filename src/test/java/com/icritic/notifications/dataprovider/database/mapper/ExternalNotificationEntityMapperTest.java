package com.icritic.notifications.dataprovider.database.mapper;

import com.icritic.notifications.core.fixture.ExternalNotificationFixture;
import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.dataprovider.database.entity.ExternalNotificationEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExternalNotificationEntityMapperTest {

    @Test
    void givenExternalNotification_thenReturnExternalNotificationEntity() {
        ExternalNotification externalNotification = ExternalNotificationFixture.load();

        ExternalNotificationEntityMapper mapper = ExternalNotificationEntityMapper.INSTANCE;

        ExternalNotificationEntity externalNotificationEntity = mapper.externalNotificationToExternalNotificationEntity(externalNotification);

        assertEquals(externalNotification.getNotificationId(), externalNotificationEntity.getNotificationId());
        assertEquals(externalNotification.getNotificationSubjectId(), externalNotificationEntity.getNotificationSubjectId());
        assertEquals(externalNotification.getNotifierId(), externalNotificationEntity.getNotifierId());
        assertEquals(externalNotification.getEmail(), externalNotificationEntity.getEmail());
        assertEquals(externalNotification.getCreatedAt(), externalNotificationEntity.getCreatedAt());
    }
}
