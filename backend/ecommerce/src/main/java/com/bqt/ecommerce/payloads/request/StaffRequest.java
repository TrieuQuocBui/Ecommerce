package com.bqt.ecommerce.payloads.request;

import lombok.Data;

import java.util.Date;

@Data
public class StaffRequest {
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String identification;

    private Date birthDay;
}
