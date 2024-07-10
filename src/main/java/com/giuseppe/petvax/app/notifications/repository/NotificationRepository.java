package com.giuseppe.petvax.app.notifications.repository;

import com.giuseppe.petvax.app.notifications.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
}
