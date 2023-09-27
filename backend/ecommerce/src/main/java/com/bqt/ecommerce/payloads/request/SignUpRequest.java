package com.bqt.ecommerce.payloads.request;

import com.bqt.ecommerce.entities.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String confirmPassword;

    private RoleRequest role;
}
