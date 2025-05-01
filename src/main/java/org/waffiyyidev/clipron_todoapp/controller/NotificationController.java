package org.waffiyyidev.clipron_todoapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.waffiyyidev.clipron_todoapp.dtos.NotificationDTO;
import org.waffiyyidev.clipron_todoapp.entity.Notification;
import org.waffiyyidev.clipron_todoapp.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {
   private final NotificationService notificationService;

   @GetMapping("/due-todos/{userId}")
   public ResponseEntity<List<NotificationDTO>> getUserNotifications(
     @PathVariable Long userId,
     @RequestParam(required = false, defaultValue = "false") boolean unreadOnly) {

      return ResponseEntity.ok(notificationService.getUserNotifications(userId, unreadOnly));
   }

   @PostMapping("/read-all/{userId}")
   public ResponseEntity<Void> markAllAsRead(@PathVariable Long userId) {
      notificationService.markAllAsRead(userId);
      return ResponseEntity.noContent().build();
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
      notificationService.deleteNotification(id);
      return ResponseEntity.noContent().build();
   }
}