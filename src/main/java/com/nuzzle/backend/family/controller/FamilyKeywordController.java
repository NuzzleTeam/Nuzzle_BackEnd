package com.nuzzle.backend.family.controller;

import com.nuzzle.backend.family.domain.mapping.FamilyKeyword;
import com.nuzzle.backend.family.dto.FamilyKeywordDTO;
import com.nuzzle.backend.family.service.FamilyKeywordService;
import com.nuzzle.backend.family.service.FamilyService;
import com.nuzzle.backend.keyword.repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/family-keywords")
public class FamilyKeywordController {

    @Autowired
    private FamilyKeywordService familyKeywordService;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private KeywordRepository keywordRepository;


    // 가족의 키워드 조회
    @GetMapping("/{familyId}")
    public ResponseEntity<List<FamilyKeywordDTO>> getKeywordsByFamily(@PathVariable Long familyId) {
        List<FamilyKeyword> familyKeywords = familyKeywordService.getKeywordsByFamily(familyId);
        List<FamilyKeywordDTO> response = familyKeywords.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // DTO 변환 메서드
    private FamilyKeywordDTO convertToDTO(FamilyKeyword familyKeyword) {
        FamilyKeywordDTO dto = new FamilyKeywordDTO();
        dto.setFamilyKeywordId(familyKeyword.getFamilyKeywordId());
        dto.setFamilyId(familyKeyword.getFamily().getFamilyId());
        dto.setKeywordId(familyKeyword.getKeyword().getKeywordId());
        dto.setKeywordName(familyKeyword.getKeyword().getKeyword());
        return dto;
    }

}
