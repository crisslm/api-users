package com.cristiandelima.api_login.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthUserDTO {

    private String username;
    private String password;

}
