package com.bqt.ecommerce.services;

import com.bqt.ecommerce.payloads.request.SupplierRequest;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.payloads.response.SupplierResponse;

import java.util.List;

public interface SupplierService {
    void createSupplier(SupplierRequest supplier);

    void updateSupplier(String idSupplier, SupplierRequest supplierRequest);

    void removeSupplier(String idSupplier);

    PaginationResponse getListSupplier(int page, int limit);


    void activeSupplier(String idSupplier);

    SupplierResponse findById(String idSupplier);
}
