package com.bqt.ecommerce.services;

import com.bqt.ecommerce.payloads.request.UserRequest;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.payloads.response.UserResponse;

public interface UserService {

    UserResponse updateUser(String image,UserRequest infoUser);

    UserResponse getUser();

    PaginationResponse getListUser(int page, int limit);
}
