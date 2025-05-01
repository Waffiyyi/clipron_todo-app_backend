package org.waffiyyidev.clipron_todoapp.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationDTO {
   private Long id;
   private String message;
   private LocalDateTime notifyAt;
   private LocalDateTime createdAt;
   private boolean read;
   private boolean sent;
   private Long todoId;
}
