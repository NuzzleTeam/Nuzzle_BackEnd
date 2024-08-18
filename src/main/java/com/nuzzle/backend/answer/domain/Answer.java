package com.nuzzle.backend.answer.domain;

import com.nuzzle.backend.question.domain.Question;
import com.nuzzle.backend.user.domain.User;
import com.nuzzle.backend.family.domain.mapping.FamilyQuestion;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answer_id")
    private Long answerId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "question_id", referencedColumnName = "question_id"),
            @JoinColumn(name = "family_id", referencedColumnName = "family_id")
    })
    private FamilyQuestion familyQuestion;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "answer_contents")
    private String answerContents;

    @Column(name = "answer_date")
    private LocalDateTime answerDate;

    @Column(name = "answer_status")
    private Boolean answerStatus;

    public void setQuestion(Question question) {

    }

    public void setContent(String content) {
    }

    public void setCreatedAt(LocalDateTime now) {
    }

    public void setAnswer(String answerText) {
    }
}
