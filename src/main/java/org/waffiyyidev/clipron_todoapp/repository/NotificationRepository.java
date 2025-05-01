package org.waffiyyidev.clipron_todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.waffiyyidev.clipron_todoapp.entity.Notification;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

   List<Notification> findByTodo_User_IdAndNotifyAtLessThanEqual(Long userId, LocalDateTime time);

   List<Notification> findByTodo_User_IdAndReadFalseAndNotifyAtLessThanEqual(Long userId, LocalDateTime time);

   List<Notification> findByTodo_User_IdAndReadFalse(Long userId);
}
