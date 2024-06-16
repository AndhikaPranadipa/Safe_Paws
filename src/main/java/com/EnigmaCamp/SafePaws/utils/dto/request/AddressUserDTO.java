package com.EnigmaCamp.SafePaws.utils.dto.request;

import com.EnigmaCamp.SafePaws.entity.AddressUser;
import com.EnigmaCamp.SafePaws.entity.City;
import com.EnigmaCamp.SafePaws.entity.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressUserDTO {

    private String cityId;

    private String description;

    public AddressUser toEntity(User reqUser, City reqCity) {
        return AddressUser.builder()
                .user(reqUser)
                .city(reqCity)
                .description(description)
                .build();
    }
}
