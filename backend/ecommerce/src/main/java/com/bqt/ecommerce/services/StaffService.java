package com.bqt.ecommerce.services;

import com.bqt.ecommerce.payloads.request.StaffRequest;
import com.bqt.ecommerce.payloads.response.StaffResponse;

public interface StaffService {
    StaffResponse getStaff();

    void updateStaff(String fileName, StaffRequest infoStaff);
}
