package org.waffiyyidev.clipron_todoapp.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.waffiyyidev.clipron_todoapp.entity.Notification;
import org.waffiyyidev.clipron_todoapp.entity.Todo;
import org.waffiyyidev.clipron_todoapp.exception.ResourceNotFoundException;
import org.waffiyyidev.clipron_todoapp.repository.NotificationRepository;
import org.waffiyyidev.clipron_todoapp.repository.TodoRepository;
import org.waffiyyidev.clipron_todoapp.service.NotificationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
   private final NotificationRepository notificationRepository;
   private final TodoRepository todoRepository;

   @Override
   public Notification createNotification(Long todoId, Notification notification) {
      Todo todo = todoRepository.findById(todoId).orElseThrow(
        () -> new ResourceNotFoundException("Todo not found", HttpStatus.NOT_FOUND));
      notification.setTodo(todo);
      return notificationRepository.save(notification);
   }

   @Override
   public List<Notification> getUserNotifications(Long userId) {
      return notificationRepository.findByTodoUserId(userId);
   }


   @Override
   public void deleteNotification(Long id) {
      notificationRepository.deleteById(id);
   }
}
