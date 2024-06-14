package com.EnigmaCamp.SafePaws.utils.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {

    private String email;

    private String password;
}
