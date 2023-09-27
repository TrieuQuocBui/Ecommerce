package com.bqt.ecommerce.services;


import com.bqt.ecommerce.payloads.request.ActiveAccountRequest;
import com.bqt.ecommerce.payloads.request.SignUpRequest;
import com.bqt.ecommerce.payloads.request.SignUpStaffRequest;
import com.bqt.ecommerce.payloads.response.PaginationResponse;

public interface AccountService {
    void createAccount(SignUpRequest signUpRequest);

    PaginationResponse getListUser(int page, int limit);

    void inactiveAccount(Long idUser);

    void activeAccount(Long idUser, ActiveAccountRequest accountRequest);

    PaginationResponse getListStaff(int page, int limit);

    void createAccountStaff(SignUpStaffRequest signUpStaffRequest);
}
