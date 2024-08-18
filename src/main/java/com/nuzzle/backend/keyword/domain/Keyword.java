package com.nuzzle.backend.keyword.domain;

import com.nuzzle.backend.family.domain.mapping.FamilyKeyword;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long keywordId;

    @Column(name = "keyword")
    private String keyword;

    @OneToMany(mappedBy = "keyword")
    private List<FamilyKeyword> familyKeywords;
}
