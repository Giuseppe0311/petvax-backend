package com.giuseppe.petvax.app.notifications.request;

import com.giuseppe.petvax.app.mail.model.EmailTemplate;
import com.giuseppe.petvax.app.notifications.model.NotificationStatus;

import java.time.LocalDateTime;

public record NotificationRequest(
        EmailTemplate emailTemplate,
        String subject,
        LocalDateTime notificationDate,
        NotificationStatus status,
        String errorMessage
) {
}
