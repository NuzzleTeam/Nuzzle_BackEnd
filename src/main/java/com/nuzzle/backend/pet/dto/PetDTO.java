package com.nuzzle.backend.pet.dto;

import lombok.Data;

@Data
public class PetDTO {
    private Long petId;
    private String petType;
    private String petImg;
}