package org.waffiyyidev.clipron_todoapp.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.waffiyyidev.clipron_todoapp.dtos.NotificationDTO;
import org.waffiyyidev.clipron_todoapp.entity.Notification;
import org.waffiyyidev.clipron_todoapp.entity.Todo;
import org.waffiyyidev.clipron_todoapp.exception.ResourceNotFoundException;
import org.waffiyyidev.clipron_todoapp.repository.NotificationRepository;
import org.waffiyyidev.clipron_todoapp.repository.TodoRepository;
import org.waffiyyidev.clipron_todoapp.service.NotificationService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {
   private final NotificationRepository notificationRepository;
   private final TodoRepository todoRepository;

   @Override
   public void createNotificationForTodo(Todo todo) {
      if (todo.getDueDate() != null) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h.a");
         String formattedDate = todo.getDueDate().format(formatter);
         String message = "Reminder: '" + todo.getTitle() + "' is due at " + formattedDate;
         Notification notification = new Notification();
         notification.setMessage(message);
         notification.setNotifyAt(todo.getDueDate().minusHours(1));
         notification.setCreatedAt(LocalDateTime.now());
         notification.setTodo(todo);
         notification.setSent(true);
         notification.setRead(false);
         notificationRepository.save(notification);
      }
   }

   @Override
   public List<NotificationDTO> getUserNotifications(Long userId, boolean unreadOnly) {
      LocalDateTime oneHourFromNow = LocalDateTime.now().plusHours(1);

      List<Notification> notifications = unreadOnly
                                         ?
                                         notificationRepository.findByTodo_User_IdAndReadFalseAndNotifyAtLessThanEqual(
                                           userId, oneHourFromNow)
                                         :
                                         notificationRepository.findByTodo_User_IdAndNotifyAtLessThanEqual(
                                           userId, oneHourFromNow);

      return notifications.stream()
                          .map(this::convertToDTO)
                          .collect(Collectors.toList());
   }

   @Override
   public void markAllAsRead(Long userId) {
      List<Notification> unreadNotifications = notificationRepository.findByTodo_User_IdAndReadFalse(
        userId);
      for (Notification notification : unreadNotifications) {
         notification.setRead(true);
      }
      notificationRepository.saveAll(unreadNotifications);
   }

   @Override
   public void deleteNotification(Long notificationId) {
      notificationRepository.deleteById(notificationId);
   }

   private NotificationDTO convertToDTO(Notification notification) {
      return NotificationDTO.builder()
                            .id(notification.getId())
                            .sent(notification.isSent())
                            .read(notification.isRead())
                            .message(notification.getMessage())
                            .notifyAt(notification.getNotifyAt())
                            .createdAt(notification.getCreatedAt())
                            .todoId(notification.getTodo().getId())
                            .build();
   }
}
