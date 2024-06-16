package com.EnigmaCamp.SafePaws.utils.dto.response;

import com.EnigmaCamp.SafePaws.entity.AddressUser;
import com.EnigmaCamp.SafePaws.entity.City;
import com.EnigmaCamp.SafePaws.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {

    private String fullName;

    private String email;

    private String phone;

    private City city;

    public static UserResponseDTO fromUser(User user, AddressUser addressUser){
        return UserResponseDTO.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .city(addressUser.getCity())
                .build();
    }
}
