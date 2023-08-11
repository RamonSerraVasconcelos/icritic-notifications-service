package com.icritic.notifications.dataprovider.kafka.mapper;

import com.icritic.notifications.core.model.PasswordReset;
import com.icritic.notifications.dataprovider.kafka.entity.PasswordResetMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class PasswordResetMessageMapper {

    public static final PasswordResetMessageMapper INSTANCE = Mappers.getMapper(PasswordResetMessageMapper.class);

    public abstract PasswordReset passwordResetMessageToPasswordReset(PasswordResetMessage passwordResetMessage);
}
