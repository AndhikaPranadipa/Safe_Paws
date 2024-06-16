package com.EnigmaCamp.SafePaws.utils.dto.request;

import com.EnigmaCamp.SafePaws.entity.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    private String fullName;

    private String email;

    private String password;

    private String phone;

    public User toEntity() {
        return User.builder()
                .fullName(fullName)
                .email(email)
                .password(password)
                .phone(phone)
                .build();
    }
}
