package com.nuzzle.backend.keyword.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    private String keyword;
}
