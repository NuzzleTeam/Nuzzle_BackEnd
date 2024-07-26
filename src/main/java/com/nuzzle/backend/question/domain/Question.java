package com.nuzzle.backend.question.domain;

import com.nuzzle.backend.family.domain.mapping.FamilyQuestion;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "question_contents")
    private String questionContents;

    @OneToMany(mappedBy = "question")
    private List<FamilyQuestion> familyQuestions;
}
