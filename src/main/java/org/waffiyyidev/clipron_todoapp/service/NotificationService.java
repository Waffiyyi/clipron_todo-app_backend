package org.waffiyyidev.clipron_todoapp.service;

import org.waffiyyidev.clipron_todoapp.dtos.NotificationDTO;
import org.waffiyyidev.clipron_todoapp.entity.Notification;
import org.waffiyyidev.clipron_todoapp.entity.Todo;

import java.util.List;

public interface NotificationService {
   List<NotificationDTO> getUserNotifications(Long userId, boolean unreadOnly);

   void markAllAsRead(Long userId);

   void createNotificationForTodo(Todo todo);
   void deleteNotification(Long notificationId);


}
