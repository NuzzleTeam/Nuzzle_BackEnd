package com.nuzzle.backend.answer.domain;

import com.nuzzle.backend.user.domain.User;
import com.nuzzle.backend.family.domain.mapping.FamilyQuestion;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "question_id", referencedColumnName = "id.questionId"),
            @JoinColumn(name = "family_id", referencedColumnName = "id.familyId")
    })
    private FamilyQuestion familyQuestion;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String answerContents;
    private LocalDateTime answerDate;
    private Boolean answerStatus;
}
