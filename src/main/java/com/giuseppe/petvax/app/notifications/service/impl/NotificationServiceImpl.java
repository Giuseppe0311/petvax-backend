package com.giuseppe.petvax.app.notifications.service.impl;

import com.giuseppe.petvax.app.notifications.exception.NotificationNotFound;
import com.giuseppe.petvax.app.notifications.model.Notification;
import com.giuseppe.petvax.app.notifications.model.NotificationStatus;
import com.giuseppe.petvax.app.notifications.repository.NotificationRepository;
import com.giuseppe.petvax.app.notifications.request.NotificationRequest;
import com.giuseppe.petvax.app.notifications.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private final NotificationRepository notificationRepository;
    @Override
    @Transactional
    public Notification saveNotification(NotificationRequest notificationRequest) {
        log.info("Saving Notification to DB");
        Notification notification = Notification.builder()
                .emailTemplate(notificationRequest.emailTemplate())
                .status(notificationRequest.status())
                .errorMessage(notificationRequest.errorMessage())
                .subject(notificationRequest.subject())
                .notificationDate(notificationRequest.notificationDate())
                .build();
        notificationRepository.save(notification);
        return notification;
    }

    @Override
    @Transactional
    public void updateNotificationStatus(Integer id, NotificationStatus status, String errorMessage) {
        log.info("Updating Notification status in DB");
        Notification notification = notificationRepository.findById(id).orElseThrow(
                ()-> new NotificationNotFound("Notification not found")
        );
        notification.setStatus(status);
        notification.setErrorMessage(errorMessage);
        notificationRepository.save(notification);
    }
}
