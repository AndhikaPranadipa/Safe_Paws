package com.EnigmaCamp.SafePaws.utils.dto.user.response;

import com.EnigmaCamp.SafePaws.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {

    private String id;

    private String fullName;

    private String email;

    private String phone;


    public static UserResponseDTO fromUser(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}
