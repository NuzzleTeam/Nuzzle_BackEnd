package com.nuzzle.backend.family.service;

import com.nuzzle.backend.family.domain.mapping.FamilyKeyword;

import java.util.List;

public interface FamilyKeywordService {

    void removeKeywordsFromFamily(Long familyId, List<Long> keywordIds);
}
