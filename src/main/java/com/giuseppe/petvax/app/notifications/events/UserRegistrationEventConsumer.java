package com.giuseppe.petvax.app.notifications.events;

import com.giuseppe.petvax.app.mail.service.EmailService;
import com.giuseppe.petvax.app.users.model.Users;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserRegistrationEventConsumer {
    private final EmailService emailService;
    @Value("${petvax.queues.reg}")
    private String queque;
    @RabbitListener(queues = "${petvax.queues.reg}")
    public void consumeUserRegistrationEvent(Users message) throws MessagingException {
        log.info("User registration event received: {}", message.getEmail());
        emailService.sendRegisterConfirmation(message.getNombres(),message.getEmail());
    }
}
