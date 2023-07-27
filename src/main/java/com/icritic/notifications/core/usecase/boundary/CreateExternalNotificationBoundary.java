package com.icritic.notifications.core.usecase.boundary;

import com.icritic.notifications.core.model.ExternalNotification;

public interface CreateExternalNotificationBoundary {

    void execute(ExternalNotification notification);
}
