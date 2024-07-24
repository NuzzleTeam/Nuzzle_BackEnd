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
    private Long questionId;

    private String questionContents;

    @OneToMany(mappedBy = "question")
    private List<FamilyQuestion> familyQuestions;
}
