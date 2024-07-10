package com.giuseppe.petvax.app.notifications.events;

import com.giuseppe.petvax.app.mail.model.EmailTemplate;
import com.giuseppe.petvax.app.mail.service.EmailService;
import com.giuseppe.petvax.app.notifications.model.Notification;
import com.giuseppe.petvax.app.notifications.model.NotificationStatus;
import com.giuseppe.petvax.app.notifications.request.NotificationRequest;
import com.giuseppe.petvax.app.notifications.service.NotificationService;
import com.giuseppe.petvax.app.users.model.Users;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserRegistrationEventConsumer {
    private final EmailService emailService;
    private final NotificationService notificationService;
    @Value("${petvax.queues.reg}")
    private String queque;
    @RabbitListener(queues = "${petvax.queues.reg}")
    public void consumeUserRegistrationEvent(Users message) throws MessagingException, UnsupportedEncodingException {
        NotificationRequest notificationRequest = new NotificationRequest(
                EmailTemplate.REGISTRATION_CONFIRMATION,
                message.getEmail(),
                LocalDateTime.now(),
                NotificationStatus.PENDING,
                null
        );
        // Guardar notificación inicialmente como PENDING
        Notification notification =  notificationService.saveNotification(notificationRequest);
        // Intentar enviar el correo y actualizar el estado
        try {
            log.info("Sending registration confirmation email to {}", message.getEmail());
            emailService.sendRegisterConfirmation(message.getNombres(), message.getEmail());
            notificationService.updateNotificationStatus(notification.getId(), NotificationStatus.SENT, null);
        } catch (MessagingException e) {
            log.error("Error sending email to {}", message.getEmail(), e);
            notificationService.updateNotificationStatus(notification.getId(), NotificationStatus.ERROR, e.getMessage());
        }
    }
}
