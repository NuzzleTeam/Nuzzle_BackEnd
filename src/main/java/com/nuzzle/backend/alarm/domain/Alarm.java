package com.nuzzle.backend.alarm.domain;

import com.nuzzle.backend.user.domain.mapping.UserAlarm;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "alarm_id")
    private Long alarmId;

    @Column(name = "contents")
    private String contents;

    @Column(name = "alarm_status")
    private Boolean alarmStatus;

    @OneToMany(mappedBy = "alarm")
    private List<UserAlarm> userAlarms;
}