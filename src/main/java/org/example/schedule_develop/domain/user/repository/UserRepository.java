package org.example.schedule_develop.domain.user.repository;

import org.example.schedule_develop.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
