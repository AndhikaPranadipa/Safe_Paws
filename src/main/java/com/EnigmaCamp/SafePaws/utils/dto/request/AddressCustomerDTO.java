package com.EnigmaCamp.SafePaws.utils.dto.request;

import com.EnigmaCamp.SafePaws.entity.AddressCustomer;
import com.EnigmaCamp.SafePaws.entity.City;
import com.EnigmaCamp.SafePaws.entity.Customer;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressCustomerDTO {

    private String cityId;

    private String description;

    public AddressCustomer toEntity(Customer reqCustomer, City reqCity) {
        return AddressCustomer.builder()
                .customer(reqCustomer)
                .city(reqCity)
                .description(description)
                .build();
    }
}
