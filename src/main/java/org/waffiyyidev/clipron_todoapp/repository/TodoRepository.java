package org.waffiyyidev.clipron_todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.waffiyyidev.clipron_todoapp.entity.Todo;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
   List<Todo> findAllByUserIdAndListId(Long userId, Long listId);
   @Query("SELECT t FROM Todo t WHERE t.completed = false AND t.dueDate BETWEEN :now AND :deadline")
   List<Todo> findTodosDueSoon(LocalDateTime now, LocalDateTime deadline);
}
