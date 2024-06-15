package com.EnigmaCamp.SafePaws.utils.dto.request;

import com.EnigmaCamp.SafePaws.entity.Shelter;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShelterDTO {

    private String name;

    private String email;

    private String password;

    private String phone;

    private String description;

    public Shelter toEntity() {
        return Shelter.builder()
                .name(name)
                .email(email)
                .password(password)
                .phone(phone)
                .description(description)
                .build();
    }
}