package com.nuzzle.backend.alarm.domain;

import com.nuzzle.backend.user.domain.mapping.UserAlarm;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Alarm {
    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.AUTO)
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> c9f35ada6c60f5934cc32d4d0762fd6c01719896
    @Column(name = "alarm_id")
    private Long alarmId;

    @Column(name = "contents")
    private String contents;

    @Column(name = "alarm_status")
    private Boolean alarmStatus;

    @OneToMany(mappedBy = "alarm")
    private List<UserAlarm> userAlarms;
}