package com.nuzzle.backend.family.domain.mapping;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class FamilyQuestionId implements Serializable {
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "family_id")
    private Long familyId;

    // 매개변수가 있는 생성자
    public FamilyQuestionId(Long questionId, Long familyId) {
        this.questionId = questionId;
        this.familyId = familyId;
    }
}
