package org.waffiyyidev.clipron_todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.waffiyyidev.clipron_todoapp.entity.Todo;

import java.util.List;
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
   List<Todo> findAllByUserIdAndListId(Long userId, Long listId);
}
