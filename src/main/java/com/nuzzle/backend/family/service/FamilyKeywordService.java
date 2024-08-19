package com.nuzzle.backend.family.service;

import com.nuzzle.backend.family.domain.mapping.FamilyKeyword;

import java.util.List;

public interface FamilyKeywordService {

    List<FamilyKeyword> getKeywordsByFamily(Long familyId);
    List<FamilyKeyword> addKeywordsToFamily(Long familyId, List<Long> keywordIds);
    void removeKeywordsFromFamily(Long familyId, List<Long> keywordIds);
}
