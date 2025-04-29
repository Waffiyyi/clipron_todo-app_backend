package org.waffiyyidev.clipron_todoapp.entity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "notifications")
@Getter
@Setter
public class Notification {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Schema(accessMode = Schema.AccessMode.READ_ONLY)
   private Long id;

   private String message;

   private LocalDateTime notifyAt;

   private boolean sent = false;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "todo_id")
   private Todo todo;
}
