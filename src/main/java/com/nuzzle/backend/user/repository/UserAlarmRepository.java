package com.nuzzle.backend.user.repository;

import com.nuzzle.backend.user.domain.mapping.UserAlarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAlarmRepository extends JpaRepository<UserAlarm, Long> {
}
