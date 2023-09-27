package com.bqt.ecommerce.payloads.response;

import com.bqt.ecommerce.payloads.request.RoleRequest;
import lombok.Data;

import java.util.Date;

@Data
public class StaffResponse {
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String identification;

    private Date birthDay;

    private String image;

    private RoleRequest role;
}
