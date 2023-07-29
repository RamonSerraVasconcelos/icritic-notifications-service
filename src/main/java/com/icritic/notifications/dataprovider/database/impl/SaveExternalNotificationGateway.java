package com.icritic.notifications.dataprovider.database.impl;

import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.core.usecase.boundary.SaveExternalNotificationBoundary;
import com.icritic.notifications.dataprovider.database.entity.ExternalNotificationEntity;
import com.icritic.notifications.dataprovider.database.mapper.ExternalNotificationEntityMapper;
import com.icritic.notifications.dataprovider.database.repository.ExternalNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveExternalNotificationGateway implements SaveExternalNotificationBoundary {

    private final ExternalNotificationRepository repository;

    public void execute(ExternalNotification notification) {
        ExternalNotificationEntityMapper mapper = ExternalNotificationEntityMapper.INSTANCE;
        ExternalNotificationEntity externalNotificationEntity = mapper.externalNotificationToExternalNotificationEntity(notification);

        repository.save(externalNotificationEntity);
    }
}
