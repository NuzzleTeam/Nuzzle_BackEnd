package com.nuzzle.backend.alarm.domain;

import com.nuzzle.backend.user.domain.mapping.UserAlarm;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alarmId;

    private String contents;
    private Boolean alarmStatus;

    @OneToMany(mappedBy = "alarm")
    private List<UserAlarm> userAlarms;
}
