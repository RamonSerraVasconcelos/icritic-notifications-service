package com.icritic.notifications.dataprovider.kafka.mapper;

import com.icritic.notifications.core.model.PasswordResetRequest;
import com.icritic.notifications.dataprovider.kafka.entity.PasswordResetRequestMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class PasswordResetRequestMessageMapper {

    public static final PasswordResetRequestMessageMapper INSTANCE = Mappers.getMapper(PasswordResetRequestMessageMapper.class);

    public abstract PasswordResetRequest passwordResetRequestMessageToPasswordResetRequest(PasswordResetRequestMessage passwordResetRequestMessage);
}
