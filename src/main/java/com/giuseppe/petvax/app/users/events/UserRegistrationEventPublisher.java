package com.giuseppe.petvax.app.users.events;

import com.giuseppe.petvax.app.users.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableRabbit
public class UserRegistrationEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void sendUserCreationEvent(Users message){
        rabbitTemplate.convertAndSend(queue.getName(), message);
    }
}
