package com.icritic.notifications.dataprovider.database.impl;

import com.icritic.notifications.core.usecase.boundary.UpdateExternalNotificationBoundary;
import com.icritic.notifications.dataprovider.database.entity.ExternalNotificationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateExternalNotificationGateway implements UpdateExternalNotificationBoundary {

    private final MongoTemplate mongoTemplate;

    public void execute(String notificationId) {
        Query query = new Query(Criteria.where("notificationId").is(notificationId));

        Update update = new Update().set("sent", true);
        mongoTemplate.updateFirst(query, update, ExternalNotificationEntity.class);
    }
}
