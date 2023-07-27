package com.icritic.notifications.dataprovider.database.repository;

import com.icritic.notifications.dataprovider.database.entity.ExternalNotificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExternalNotificationRepository extends MongoRepository<ExternalNotificationEntity, String> {
}
