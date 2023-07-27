package com.icritic.notifications.dataprovider.database.mapper;

import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.dataprovider.database.entity.ExternalNotificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ExternalNotificationEntityMapper {

    public static final ExternalNotificationEntityMapper INSTANCE = Mappers.getMapper(ExternalNotificationEntityMapper.class);

    public abstract ExternalNotificationEntity externalNotificationToExternalNotificationEntity(ExternalNotification externalNotification);

    public abstract ExternalNotification externalNotificationEntityToExternalNotification(ExternalNotificationEntity externalNotificationEntity);
}
