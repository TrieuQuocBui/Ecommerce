package com.bqt.ecommerce.payloads.response;

import lombok.Data;

import java.util.Date;
@Data
public class UserResponse {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String identification;

    private Date birthDay;

    private String image;
}
