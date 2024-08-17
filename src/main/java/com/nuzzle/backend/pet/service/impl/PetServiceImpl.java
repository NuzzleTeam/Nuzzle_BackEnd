package com.nuzzle.backend.pet.service.impl;

import com.nuzzle.backend.pet.domain.Pet;
import com.nuzzle.backend.pet.dto.PetDTO;
import com.nuzzle.backend.pet.repository.PetRepository;
import com.nuzzle.backend.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public PetDTO getRandomPetType() {
        List<Pet> allPets = petRepository.findAll();
        if (allPets.isEmpty()) {
            throw new IllegalStateException("No pets available.");
        }
        Random random = new Random();
        Pet randomPet = allPets.get(random.nextInt(allPets.size()));
        return convertToDTO(randomPet);
    }

    private PetDTO convertToDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setPetId(pet.getPetId());
        petDTO.setPetType(pet.getPetType());
        petDTO.setPetImg(pet.getPetImg());
        return petDTO;
    }
}
