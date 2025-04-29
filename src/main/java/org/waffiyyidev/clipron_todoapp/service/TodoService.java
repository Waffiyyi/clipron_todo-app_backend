package org.waffiyyidev.clipron_todoapp.service;

import org.waffiyyidev.clipron_todoapp.entity.Todo;
import org.waffiyyidev.clipron_todoapp.entity.TodoList;

import java.util.List;

public interface TodoService {
   TodoList createList(Long userId, String name);
   List<TodoList> getTodoListByUser(Long userId);

   Todo createTodo(Long userId, Todo todo, Long listId);
   List<Todo> getTodosByUser(Long userId, Long listId);
   Todo updateTodo(Long id, Todo updatedTodo);
   void deleteTodo(Long id);
}
