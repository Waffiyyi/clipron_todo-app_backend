package org.waffiyyidev.clipron_todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.waffiyyidev.clipron_todoapp.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmail(String email);
}
