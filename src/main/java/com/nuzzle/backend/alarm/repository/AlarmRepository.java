package com.nuzzle.backend.alarm.repository;

import com.nuzzle.backend.alarm.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
