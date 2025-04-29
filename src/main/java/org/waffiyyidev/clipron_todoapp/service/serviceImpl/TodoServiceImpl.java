package org.waffiyyidev.clipron_todoapp.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.waffiyyidev.clipron_todoapp.entity.Todo;
import org.waffiyyidev.clipron_todoapp.entity.TodoList;
import org.waffiyyidev.clipron_todoapp.entity.User;
import org.waffiyyidev.clipron_todoapp.exception.ResourceNotFoundException;
import org.waffiyyidev.clipron_todoapp.exception.UserNotFoundException;
import org.waffiyyidev.clipron_todoapp.repository.TodoListRepository;
import org.waffiyyidev.clipron_todoapp.repository.TodoRepository;
import org.waffiyyidev.clipron_todoapp.repository.UserRepository;
import org.waffiyyidev.clipron_todoapp.service.TodoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
   private final TodoRepository todoRepository;
   private final UserRepository userRepository;
   private final TodoListRepository todoListRepository;

   @Override
   public TodoList createList(Long userId, String name) {
      User user = userRepository.findById(userId).orElseThrow(
        () -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));

      TodoList list = new TodoList();
      list.setName(name);
      list.setUser(user);

      return todoListRepository.save(list);
   }

   @Override
   public List<TodoList> getTodoListByUser(Long userId) {
      return todoListRepository.findAllByUserId(userId);
   }

   @Override
   public Todo createTodo(Long userId, Todo todo, Long listId) {
      User user = userRepository.findById(userId).orElseThrow(
        () -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));

      TodoList list = todoListRepository.findById(listId).orElseThrow(
        () -> new ResourceNotFoundException("List not found", HttpStatus.NOT_FOUND));

      todo.setUser(user);
      todo.setList(list);
      Todo savedTodo = todoRepository.save(todo);
      list.getTodos().add(savedTodo);
      todoListRepository.save(list);
      return savedTodo;
   }

   @Override
   public List<Todo> getTodosByUser(Long userId, Long listId) {
      return todoRepository.findAllByUserIdAndListId(userId, listId);
   }

   @Override
   public Todo updateTodo(Long id, Todo updatedTodo) {
      Todo todo = todoRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Todo not found", HttpStatus.NOT_FOUND));

      if (updatedTodo.getTitle() != null) {
         todo.setTitle(updatedTodo.getTitle());
      }
      if (updatedTodo.getDescription() != null) {
         todo.setDescription(updatedTodo.getDescription());
      }
      if (updatedTodo.getDueDate() != null) {
         todo.setDueDate(updatedTodo.getDueDate());
      }
      if (updatedTodo.getPriority() != null) {
         todo.setPriority(updatedTodo.getPriority());
      }
      todo.setStarred(updatedTodo.isStarred());
      todo.setCompleted(updatedTodo.isCompleted());


      return todoRepository.save(todo);
   }

   @Override
   public void deleteTodo(Long id) {
      todoRepository.deleteById(id);
   }
}
