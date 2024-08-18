package com.nuzzle.backend.user.domain;

import com.nuzzle.backend.answer.domain.Answer;
import com.nuzzle.backend.family.domain.Family;
import com.nuzzle.backend.picture.domain.Picture;
import com.nuzzle.backend.user.domain.mapping.UserAlarm;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    @OneToMany(mappedBy = "user")
    private List<Answer> answers;

    @OneToMany(mappedBy = "user")
    private List<UserAlarm> userAlarms;

    @OneToMany(mappedBy = "user")
    private List<Picture> pictures;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "serial_id")
    private String serialId;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;
}
