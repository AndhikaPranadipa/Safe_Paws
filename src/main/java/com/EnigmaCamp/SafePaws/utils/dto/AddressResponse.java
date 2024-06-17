package com.EnigmaCamp.SafePaws.utils.dto;

import com.EnigmaCamp.SafePaws.entity.AddressShelter;
import com.EnigmaCamp.SafePaws.entity.AddressUser;
import com.EnigmaCamp.SafePaws.entity.City;
import com.EnigmaCamp.SafePaws.entity.Province;
import com.EnigmaCamp.SafePaws.repository.CityRepository;
import com.EnigmaCamp.SafePaws.repository.ProvinceRepository;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private String id;

    private String province;

    private String city;

    private String description;

    public static AddressResponse toResponse(AddressUser addressUser, City city, Province province) {

        return AddressResponse.builder()
                .id(addressUser.getId())
                .province(province.getProvince())
                .city(city.getCity())
                .description(addressUser.getDescription())
                .build();
    }

    public static AddressResponse toResponse(AddressShelter addressShelter, City city, Province province) {

        return AddressResponse.builder()
                .id(addressShelter.getId())
                .province(province.getProvince())
                .city(city.getCity())
                .description(addressShelter.getDescription())
                .build();
    }
}
