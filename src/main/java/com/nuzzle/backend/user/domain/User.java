package com.nuzzle.backend.user.domain;

import com.nuzzle.backend.answer.domain.Answer;
import com.nuzzle.backend.family.domain.Family;
import com.nuzzle.backend.global.dto.type.EFamilyRole;
import com.nuzzle.backend.global.dto.type.EProvider;
import com.nuzzle.backend.global.dto.type.ERole;
import com.nuzzle.backend.picture.domain.Picture;
import com.nuzzle.backend.user.domain.mapping.UserAlarm;
import com.nuzzle.backend.user.dto.AuthSignUpDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
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

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole role;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private EProvider provider;

    @Column(name="familyrole",nullable = false)
    @Enumerated(EnumType.STRING)
    private EFamilyRole familyRole;

    @Column(name = "is_login", columnDefinition = "TINYINT(1)")
    private Boolean isLogin;

    private String refreshToken;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public User(Family family, List<Answer> answers, List<Picture> pictures, String userName, String gender, String serialId, String password, LocalDate birthDate, String refreshToken, EProvider provider, EFamilyRole familyRole, ERole role) {
        this.family = family;
        this.answers = answers;
        this.pictures = pictures;
        this.userName = userName;
        this.gender = gender;
        this.serialId = serialId;
        this.password = password;
        this.role = role;
        this.familyRole = familyRole;
        this.birthDate = birthDate;
        this.provider = provider;
        this.isLogin = false;
        this.refreshToken = refreshToken;
        this.createdAt = LocalDateTime.now();
    }


    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    // 소셜 회원가입
    public static User signUp(String serialId, EProvider provider) {
        return User.builder()
                .serialId(serialId)
                .provider(provider)
                .password(null)
                .role(ERole.USER)
                .build();
    }

    // 자체 회원가입
    public static User signUp(AuthSignUpDto authSignUpDto, String encodedPassword) {
        return User.builder()
                .serialId(authSignUpDto.serialId())
                .password(encodedPassword)
                .provider(EProvider.DEFAULT)
                .role(ERole.USER)
                .familyRole(authSignUpDto.familyrole())
                .userName(authSignUpDto.userName())
                .gender(authSignUpDto.gender())
                .birthDate(authSignUpDto.birthDate())
                .build();
    }

}

