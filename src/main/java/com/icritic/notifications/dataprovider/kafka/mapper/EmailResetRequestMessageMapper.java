package com.icritic.notifications.dataprovider.kafka.mapper;

import com.icritic.notifications.core.model.EmailResetRequest;
import com.icritic.notifications.dataprovider.kafka.entity.EmailResetRequestMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class EmailResetRequestMessageMapper {

    public static EmailResetRequestMessageMapper INSTANCE = Mappers.getMapper(EmailResetRequestMessageMapper.class);

    public abstract EmailResetRequest emailResetRequestMessageToemailResetRequest(EmailResetRequestMessage emailResetRequestMessage);
}
