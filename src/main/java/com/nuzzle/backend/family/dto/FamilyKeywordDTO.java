package com.nuzzle.backend.family.dto;

import lombok.Data;

import java.util.List;

@Data
public class FamilyKeywordDTO {
    private Long familyKeywordId;
    private Long familyId;
    private Long keywordId;
    private String keywordName;

    @Data
    public static class AddKeywordsRequest {
        private Long familyId;
        private List<Long> keywordIds;
    }

    @Data
    public static class RemoveKeywordsRequest {  // 여러 키워드 삭제 요청을 위한 클래스
        private Long familyId;
        private List<Long> keywordIds;
    }
}
