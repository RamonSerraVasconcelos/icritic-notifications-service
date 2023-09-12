package com.icritic.notifications.dataprovider.kafka.mapper;

import com.icritic.notifications.core.model.EmailReset;
import com.icritic.notifications.dataprovider.kafka.entity.EmailResetMessage;
import com.icritic.notifications.dataprovider.kafka.entity.EmailResetRequestMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class EmailResetMessageMapper {

    public static EmailResetMessageMapper INSTANCE = Mappers.getMapper(EmailResetMessageMapper.class);

    public abstract EmailReset emailResetMessageToEmailReset(EmailResetMessage emailResetMessage);
}
