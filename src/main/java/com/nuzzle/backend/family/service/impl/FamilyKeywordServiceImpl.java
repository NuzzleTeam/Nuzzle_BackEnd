package com.nuzzle.backend.family.service.impl;

import com.nuzzle.backend.family.domain.Family;
import com.nuzzle.backend.family.domain.mapping.FamilyKeyword;
import com.nuzzle.backend.family.repository.FamilyKeywordRepository;
import com.nuzzle.backend.family.repository.FamilyRepository;
import com.nuzzle.backend.family.service.FamilyKeywordService;
import com.nuzzle.backend.keyword.domain.Keyword;
import com.nuzzle.backend.keyword.repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FamilyKeywordServiceImpl implements FamilyKeywordService {

    @Autowired
    private FamilyKeywordRepository familyKeywordRepository;

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Override
    public List<FamilyKeyword> getKeywordsByFamily(Long familyId) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new IllegalArgumentException("가족을 찾을 수 없습니다."));
        return familyKeywordRepository.findByFamily(family);
    }
}
