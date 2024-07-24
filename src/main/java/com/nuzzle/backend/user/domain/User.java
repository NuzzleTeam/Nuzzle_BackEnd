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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "familyId")
    private Family family;

    @OneToMany(mappedBy = "user")
    private List<Answer> answers;

    @OneToMany(mappedBy = "user")
    private List<UserAlarm> userAlarms;

    @OneToMany(mappedBy = "user")
    private List<Picture> pictures;

    private String userName;
    private String gender;
    private String serialId;
    private String password;
    private String role;
    private LocalDateTime birthDate;
}
