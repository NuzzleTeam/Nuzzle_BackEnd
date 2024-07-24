package com.nuzzle.backend.family.domain.mapping;

import com.nuzzle.backend.answer.domain.Answer;
import com.nuzzle.backend.family.domain.Family;
import com.nuzzle.backend.question.domain.Question;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class FamilyQuestion {
    @EmbeddedId
    private FamilyQuestionId id;

    @ManyToOne
    @JoinColumn(name = "familyId")
    private Family family;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    @OneToMany(mappedBy = "answer")
    private List<Answer> answers;
}
