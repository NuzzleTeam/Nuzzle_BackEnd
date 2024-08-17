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



    // 여러 키워드를 가족에서 제거
    @DeleteMapping("/remove-multiple")
    public ResponseEntity<Void> removeKeywordsFromFamily(@RequestBody FamilyKeywordDTO.RemoveKeywordsRequest request) {
        familyKeywordService.removeKeywordsFromFamily(request.getFamilyId(), request.getKeywordIds());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
