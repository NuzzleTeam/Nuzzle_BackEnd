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
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "alarmId")
    private Alarm alarm;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private LocalDateTime alarmTime;

}
