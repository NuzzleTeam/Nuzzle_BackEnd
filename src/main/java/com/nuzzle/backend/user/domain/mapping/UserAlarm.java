package com.nuzzle.backend.user.domain.mapping;

import com.nuzzle.backend.alarm.domain.Alarm;
import com.nuzzle.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserAlarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_alarm_id")
    private Long userAlarmId;

    @ManyToOne
    @JoinColumn(name = "alarm_id")
    private Alarm alarm;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "alarm_time")
    private LocalDateTime alarmTime;
}
