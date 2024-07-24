package com.nuzzle.backend.family.domain.mapping;

import com.nuzzle.backend.family.domain.Family;
import com.nuzzle.backend.keyword.domain.Keyword;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FamilyKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    @ManyToOne
    @JoinColumn(name = "familyId")
    private Family family;

    @ManyToOne
    @JoinColumn(name = "keywordId")
    private Keyword keyword;
}
