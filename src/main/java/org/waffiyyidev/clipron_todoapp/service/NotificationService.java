package org.waffiyyidev.clipron_todoapp.service;

import org.waffiyyidev.clipron_todoapp.entity.Notification;

import java.util.List;

public interface NotificationService {
   Notification createNotification(Long todoId, Notification notification);
   List<Notification> getUserNotifications(Long userId);
   void deleteNotification(Long id);
}
