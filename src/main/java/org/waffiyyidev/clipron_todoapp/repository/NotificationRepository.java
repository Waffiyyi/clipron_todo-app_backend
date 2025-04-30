package org.waffiyyidev.clipron_todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.waffiyyidev.clipron_todoapp.entity.Notification;

import java.util.List;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
   List<Notification> findByTodoUserId(Long userId);
   @Query("SELECT n FROM Notification n WHERE n.todo.user.id = :userId ORDER BY n.notifyAt DESC")
   List<Notification> findAllByTodo_User_IdOrderByNotifyAtDesc(@Param("userId") Long userId);
}
