package com.EnigmaCamp.SafePaws.utils.dto.request;

import com.EnigmaCamp.SafePaws.entity.Customer;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDTO {

    private String fullName;

    private String email;

    private String password;

    private String phone;

    public Customer toEntity() {
        return Customer.builder()
                .fullName(fullName)
                .email(email)
                .password(password)
                .phone(phone)
                .build();
    }
}
