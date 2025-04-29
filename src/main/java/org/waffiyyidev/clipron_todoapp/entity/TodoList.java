package org.waffiyyidev.clipron_todoapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todo_lists")
@Getter
@Setter
public class TodoList {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Schema(accessMode = Schema.AccessMode.READ_ONLY)
   private Long id;

   private String name;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id")
   private User user;

   @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
   @JsonManagedReference
   private List<Todo> todos = new ArrayList<>();
}