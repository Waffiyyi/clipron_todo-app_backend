package org.waffiyyidev.clipron_todoapp.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.waffiyyidev.clipron_todoapp.entity.Notification;
import org.waffiyyidev.clipron_todoapp.entity.Todo;
import org.waffiyyidev.clipron_todoapp.exception.ResourceNotFoundException;
import org.waffiyyidev.clipron_todoapp.repository.NotificationRepository;
import org.waffiyyidev.clipron_todoapp.repository.TodoRepository;
import org.waffiyyidev.clipron_todoapp.service.NotificationService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {
   private final NotificationRepository notificationRepository;
   private final TodoRepository todoRepository;

   //   @Override
   //   public Notification createNotification(Long todoId, Notification notification) {
   //      Todo todo = todoRepository.findById(todoId).orElseThrow(
   //        () -> new ResourceNotFoundException("Todo not found", HttpStatus.NOT_FOUND));
   //      notification.setTodo(todo);
   //      return notificationRepository.save(notification);
   //   }
   //
   //
   //   @Override
   //   public void deleteNotification(Long id) {
   //      notificationRepository.deleteById(id);
   //   }
   @Override
   @Scheduled(fixedRate = 300000)
   public void generateDueSoonNotifications() {
      LocalDateTime now = LocalDateTime.now();
      LocalDateTime deadline = now.plusHours(1);

      List<Todo> todosDueSoon = todoRepository.findTodosDueSoon(now, deadline);

      for (Todo todo : todosDueSoon) {
         boolean alreadyNotified = todo.getNotifications().stream()
                                       .anyMatch(n -> !n.isSent() && n
                                         .getNotifyAt()
                                         .isAfter(now.minusMinutes(10)));

         if (!alreadyNotified) {
            Notification notification = new Notification();
            notification.setMessage(
              "Your task \"" + todo.getTitle() + "\" is due at " + todo.getDueDate());
            notification.setTodo(todo);
            notification.setNotifyAt(now);
            notification.setSent(false);
            notificationRepository.save(notification);
            log.info("Notification created for todo: {}", todo.getTitle());
         }
      }
   }

   @Override
   public List<Notification> getUserNotifications(Long userId) {
      return notificationRepository.findAllByTodo_User_IdOrderByNotifyAtDesc(userId);
   }
}
