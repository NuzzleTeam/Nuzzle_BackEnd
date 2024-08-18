package com.nuzzle.backend.question.domain;

import com.nuzzle.backend.family.domain.mapping.FamilyQuestion;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "question_contents")
    private String questionContents;

    @OneToMany(mappedBy = "question")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FamilyQuestion> familyQuestions;
}
