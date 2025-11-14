package org.example.schedule_develop.domain.user.repository;

import java.util.Optional;
import org.example.schedule_develop.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
