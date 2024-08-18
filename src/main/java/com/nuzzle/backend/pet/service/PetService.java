package com.nuzzle.backend.pet.service;

import com.nuzzle.backend.pet.dto.PetDTO;

public interface PetService {
    PetDTO getRandomPetType(); // 랜덤으로 하나의 펫을 반환하는 메서드
}
