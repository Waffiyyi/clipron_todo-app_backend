package org.waffiyyidev.clipron_todoapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.waffiyyidev.clipron_todoapp.entity.Todo;
import org.waffiyyidev.clipron_todoapp.entity.TodoList;
import org.waffiyyidev.clipron_todoapp.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
   private final TodoService todoService;


   @PostMapping("/create-list/{userId}")
   public ResponseEntity<TodoList> createList(@PathVariable Long userId,
                                              @RequestParam String name) {
      TodoList list = todoService.createList(userId, name);
      return ResponseEntity.ok(list);
   }

   @GetMapping("/get-list/{userId}")
   public ResponseEntity<List<TodoList>> getUserTodoLists(@PathVariable Long userId) {
      return ResponseEntity.ok(todoService.getTodoListByUser(userId));
   }

   @DeleteMapping("/delete-list/{id}")
   public ResponseEntity<Void> deleteTodoList(@PathVariable Long id) {
      todoService.deleteTodoList(id);
      return ResponseEntity.noContent().build();
   }


   @PostMapping("/create/{userId}")
   public ResponseEntity<Todo> createTodo(@PathVariable Long userId, @RequestParam Long listId,
                                          @RequestBody Todo todo) {
      return ResponseEntity.ok(todoService.createTodo(userId, todo, listId));
   }

   @GetMapping("/user/{userId}")
   public ResponseEntity<List<Todo>> getUserTodos(@PathVariable Long userId,
                                                  @RequestParam Long listId) {
      return ResponseEntity.ok(todoService.getTodosByUser(userId, listId));
   }

   @PutMapping("/update/{id}")
   public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
      return ResponseEntity.ok(todoService.updateTodo(id, todo));
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
      todoService.deleteTodo(id);
      return ResponseEntity.noContent().build();
   }
}
