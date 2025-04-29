package org.waffiyyidev.clipron_todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.waffiyyidev.clipron_todoapp.entity.TodoList;
import org.waffiyyidev.clipron_todoapp.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
   List<TodoList> findAllByUserId(Long userId);
   Optional<TodoList> findByUserAndName(User user, String name);
}
