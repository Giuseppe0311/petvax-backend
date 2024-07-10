package com.giuseppe.petvax.app.notifications.service;

import com.giuseppe.petvax.app.notifications.model.Notification;
import com.giuseppe.petvax.app.notifications.model.NotificationStatus;
import com.giuseppe.petvax.app.notifications.request.NotificationRequest;

public interface NotificationService {
    Notification saveNotification(NotificationRequest notificationRequest);
    void updateNotificationStatus(Integer id, NotificationStatus status, String errorMessage);
}
