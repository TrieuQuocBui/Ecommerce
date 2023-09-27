package com.bqt.ecommerce.services.impl;

import com.bqt.ecommerce.entities.Brand;
import com.bqt.ecommerce.entities.Order;
import com.bqt.ecommerce.exceptions.NotFoundException;
import com.bqt.ecommerce.payloads.request.BrandRequest;
import com.bqt.ecommerce.payloads.response.BrandResponse;
import com.bqt.ecommerce.payloads.response.OrderResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.repositories.BrandRepository;
import com.bqt.ecommerce.services.BrandService;
import com.bqt.ecommerce.services.FileService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private FileService fileService;

    @Value("${spring.resources.static-locations}")
    private String path;

    @Override
    public List<BrandResponse> getAllActiveBrand(boolean status) {
        List<Brand> brands = brandRepository.findByStatus(status);

        Type listType = new TypeToken<List<BrandResponse>>(){}.getType();

        List<BrandResponse> brandResponses = modelMapper.map(brands,listType);
        return brandResponses;
    }

    @Override
    public void createBrand(String fileName, BrandRequest brandRequest) {
        Brand brand = this.modelMapper.map(brandRequest,Brand.class);
        brand.setImg(fileName);
        this.brandRepository.save(brand);
    }

    @Override
    public void updateBrand(Long idBrand, String fileName, BrandRequest brandRequest) {
        Brand findBrand = brandRepository.findById(idBrand).orElseThrow(() -> new NotFoundException("Không tìm thấy thương hiệu"));

        if (brandRequest != null){
            String file = findBrand.getImg();
            brandRequest.setImg(file);
            this.modelMapper.map(brandRequest,findBrand);
        } else {
            this.fileService.deleteImage(path,findBrand.getImg());
            findBrand.setImg(fileName);
        }
        this.brandRepository.save(findBrand);
    }

    @Override
    public void removeBrand(Long idBrand) {
        Brand findBrand = brandRepository.findById(idBrand).orElseThrow(() -> new NotFoundException("Không tìm thấy thương hiệu"));
            this.fileService.deleteImage(path,findBrand.getImg());
            this.brandRepository.delete(findBrand);
    }

    @Override
    public PaginationResponse getBrandList(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1,limit);

        Page brandPage = this.brandRepository.findAll(pageable);

        List<Brand> pageContent = brandPage.getContent();

        Type listType = new TypeToken<List<BrandResponse>>(){}.getType();

        List<BrandResponse> BrandResponse = modelMapper.map(pageContent,listType);

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(BrandResponse);
        paginationResponse.setLastPage(brandPage.isLast());
        paginationResponse.setPageNumber(brandPage.getNumber());
        paginationResponse.setPageSize(brandPage.getSize());
        paginationResponse.setTotalElements(brandPage.getTotalElements());
        paginationResponse.setTotalPages(brandPage.getTotalPages());
        return paginationResponse;
    }

    @Override
    public BrandResponse getBrandById(Long idBrand) {
        Brand brand = brandRepository.findById(idBrand).orElseThrow(() -> new NotFoundException("Không tìm thấy hãng"));
        return this.modelMapper.map(brand,BrandResponse.class);
    }

    @Override
    public List<BrandResponse> getAllBrand() {
        List<Brand> brands = this.brandRepository.findAll();

        Type listType = new TypeToken<List<BrandResponse>>(){}.getType();

        List<BrandResponse> BrandResponse = modelMapper.map(brands,listType);

        return BrandResponse;
    }
}
