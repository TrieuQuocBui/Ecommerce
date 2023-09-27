package com.bqt.ecommerce.payloads.response;

import com.bqt.ecommerce.payloads.request.RoleRequest;
import lombok.Data;

@Data
public class AccountResponse {
    private int id;

    private String username;

    private RoleRequest role;

    private boolean status;

    private UserResponse user;

    private StaffResponse staff;
}
