package com.icritic.notifications.dataprovider.kafka.mapper;

import com.icritic.notifications.core.model.PasswordChange;
import com.icritic.notifications.dataprovider.kafka.entity.PasswordChangeMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class PasswordChangeMessageMapper {

    public static PasswordChangeMessageMapper INSTANCE = Mappers.getMapper(PasswordChangeMessageMapper.class);

    public abstract PasswordChange passwordChangeMessageToPasswordChange(PasswordChangeMessage passwordChangeMessage);
}
