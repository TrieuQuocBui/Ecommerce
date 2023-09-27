package com.bqt.ecommerce.services;

import com.bqt.ecommerce.payloads.request.BrandRequest;
import com.bqt.ecommerce.payloads.response.BrandResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BrandService {
    List<BrandResponse> getAllActiveBrand(boolean b);

    void createBrand(String fileName, BrandRequest brandRequest);

    void updateBrand(Long idBrand, String fileName, BrandRequest brandRequest);

    void removeBrand(Long idBrand);

    PaginationResponse getBrandList(int page, int limit);

    BrandResponse getBrandById(Long idBrand);

    List<BrandResponse> getAllBrand();
}
