package com.EnigmaCamp.SafePaws.utils.dto.user.request;

import com.EnigmaCamp.SafePaws.entity.AddressUser;
import com.EnigmaCamp.SafePaws.entity.City;
import com.EnigmaCamp.SafePaws.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateAddressUserDTO {

    @NotBlank
    private String id;

    @NotBlank
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
