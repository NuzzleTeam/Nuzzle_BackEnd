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
    @Column(name = "family_keyword_id")
    private Long familyKeywordId;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    @ManyToOne
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;
}
