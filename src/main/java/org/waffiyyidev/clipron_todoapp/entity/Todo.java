package org.waffiyyidev.clipron_todoapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.waffiyyidev.clipron_todoapp.enums.Priority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todos")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Todo {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String title;
   private String description;
   private boolean completed = false;
   private LocalDateTime dueDate;

   @Enumerated(EnumType.STRING)
   private Priority priority;

   private boolean starred = false;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id")
   private User user;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "list_id")
   @JsonBackReference
   private TodoList list;

   @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Notification> notifications = new ArrayList<>();
}