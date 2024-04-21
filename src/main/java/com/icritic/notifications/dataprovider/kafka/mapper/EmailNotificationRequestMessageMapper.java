package com.icritic.notifications.dataprovider.kafka.mapper;

import com.icritic.notifications.core.model.EmailNotificationRequest;
import com.icritic.notifications.dataprovider.kafka.entity.EmailNotificationRequestMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class EmailNotificationRequestMessageMapper {

    public static EmailNotificationRequestMessageMapper INSTANCE = Mappers.getMapper(EmailNotificationRequestMessageMapper.class);

    public abstract EmailNotificationRequest messageToModel(EmailNotificationRequestMessage emailNotificationRequestMessage);
}
