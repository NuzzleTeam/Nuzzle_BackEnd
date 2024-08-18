package com.nuzzle.backend.question.domain;

import com.nuzzle.backend.family.domain.mapping.FamilyQuestion;
import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;
=======
import lombok.Data;
>>>>>>> 0fea6e0a7e3363f534cc21777b34438b51ee72c1

import java.util.List;

@Entity
<<<<<<< HEAD
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
=======
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> 0fea6e0a7e3363f534cc21777b34438b51ee72c1
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "question_contents")
    private String questionContents;

    @OneToMany(mappedBy = "question")
<<<<<<< HEAD
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
=======
>>>>>>> 0fea6e0a7e3363f534cc21777b34438b51ee72c1
    private List<FamilyQuestion> familyQuestions;
}
