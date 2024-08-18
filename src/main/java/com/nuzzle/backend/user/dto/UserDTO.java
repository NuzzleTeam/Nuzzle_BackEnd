package com.nuzzle.backend.user.dto;

import com.nuzzle.backend.family.dto.FamilyDTO;
import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String userName;
    private String gender;
    private String serialId;
    private String password;
    private String role;
    private String birthDate;
    private FamilyDTO family;
}