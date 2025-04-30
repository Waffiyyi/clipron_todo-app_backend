package org.waffiyyidev.clipron_todoapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.waffiyyidev.clipron_todoapp.entity.Notification;
import org.waffiyyidev.clipron_todoapp.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {
   private final NotificationService notificationService;

   //   @PostMapping("/{todoId}")
   //   public ResponseEntity<Notification> createNotification(@PathVariable Long todoId,
   //                                                          @RequestBody Notification notification) {
   //      return ResponseEntity.ok(notificationService.createNotification(todoId, notification));
   //   }
   //
   //   @GetMapping("/user/{userId}")
   //   public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
   //      return ResponseEntity.ok(notificationService.getUserNotifications(userId));
   //   }
   //
   //   @DeleteMapping("/{id}")
   //   public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
   //      notificationService.deleteNotification(id);
   //      return ResponseEntity.noContent().build();
   //   }

   @GetMapping("/due-todos/{userId}")
   public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
      return ResponseEntity.ok(notificationService.getUserNotifications(userId));
   }
}