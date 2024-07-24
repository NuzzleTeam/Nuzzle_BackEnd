package com.nuzzle.backend.user.repository;

import com.nuzzle.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
