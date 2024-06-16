package com.EnigmaCamp.SafePaws.utils.dto.request;

import com.EnigmaCamp.SafePaws.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    @NotBlank
    private String fullName;

    @NotBlank
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank
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
