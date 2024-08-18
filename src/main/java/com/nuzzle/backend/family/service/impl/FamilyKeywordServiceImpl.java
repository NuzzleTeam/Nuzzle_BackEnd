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
    @Transactional
    public List<FamilyKeyword> addKeywordsToFamily(Long familyId, List<Long> keywordIds) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new IllegalArgumentException("가족을 찾을 수 없습니다."));

        // 이미 존재하는 키워드를 필터링하여 중복을 제거
        List<FamilyKeyword> existingKeywords = familyKeywordRepository.findByFamily(family);
        List<Long> existingKeywordIds = existingKeywords.stream()
                .map(fk -> fk.getKeyword().getKeywordId())
                .collect(Collectors.toList());

        List<FamilyKeyword> newFamilyKeywords = keywordIds.stream()
                .filter(keywordId -> !existingKeywordIds.contains(keywordId))
                .map(keywordId -> {
                    Keyword keyword = keywordRepository.findById(keywordId)
                            .orElseThrow(() -> new IllegalArgumentException("키워드를 찾을 수 없습니다."));
                    FamilyKeyword familyKeyword = new FamilyKeyword();
                    familyKeyword.setFamily(family);
                    familyKeyword.setKeyword(keyword);
                    return familyKeyword;
                })
                .collect(Collectors.toList());

        return familyKeywordRepository.saveAll(newFamilyKeywords);
    }

    @Override
    @Transactional
    public void removeKeywordsFromFamily(Long familyId, List<Long> keywordIds) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new IllegalArgumentException("가족을 찾을 수 없습니다."));

        List<FamilyKeyword> familyKeywordsToDelete = familyKeywordRepository.findByFamily(family).stream()
                .filter(fk -> keywordIds.contains(fk.getKeyword().getKeywordId()))
                .collect(Collectors.toList());

        if (familyKeywordsToDelete.isEmpty()) {
            throw new IllegalArgumentException("삭제할 키워드가 없습니다.");
        }

        familyKeywordRepository.deleteAll(familyKeywordsToDelete);
    }

    @Override
    public List<FamilyKeyword> getKeywordsByFamily(Long familyId) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new IllegalArgumentException("가족을 찾을 수 없습니다."));
        return familyKeywordRepository.findByFamily(family);
    }
}
