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
    private FamilyQuestionId familyQuestionId;

    @ManyToOne
    @MapsId("familyId")
    @JoinColumn(name = "family_id")
    private Family family;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "familyQuestion")
    private List<Answer> answers;
}
