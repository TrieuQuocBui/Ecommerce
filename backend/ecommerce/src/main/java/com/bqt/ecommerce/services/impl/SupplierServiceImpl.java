package com.bqt.ecommerce.services.impl;

import com.bqt.ecommerce.entities.Brand;
import com.bqt.ecommerce.entities.Supplier;
import com.bqt.ecommerce.exceptions.BadRequestException;
import com.bqt.ecommerce.exceptions.NotFoundException;
import com.bqt.ecommerce.payloads.request.SupplierRequest;
import com.bqt.ecommerce.payloads.response.BrandResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.payloads.response.SupplierResponse;
import com.bqt.ecommerce.repositories.SupplierRepository;
import com.bqt.ecommerce.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createSupplier(SupplierRequest supplierRequest) {
        Supplier mapperSupplier = this.modelMapper.map(supplierRequest,Supplier.class);
        boolean  checkSupplier = this.supplierRepository.findById(mapperSupplier.getId()).isPresent();
        if (checkSupplier){
            throw new BadRequestException("Mã nhà cung cấp đã tồn tại");
        } else {
        this.supplierRepository.save(mapperSupplier);
        }
    }

    @Override
    public void updateSupplier(String idSupplier, SupplierRequest supplierRequest) {
        Supplier findSupplier = this.supplierRepository.findById(idSupplier).orElseThrow( () -> new NotFoundException("Mã nhà cung cấp không tồn tại"));
        supplierRequest.setId(idSupplier);
        this.modelMapper.map(supplierRequest,findSupplier);
        this.supplierRepository.save(findSupplier);
    }

    @Override
    public void removeSupplier(String idSupplier) {
        Supplier findSupplier = this.supplierRepository.findById(idSupplier).orElseThrow( () -> new NotFoundException("Mã nhà cung cấp không tồn tại"));

        if (!findSupplier.getReceipt().isEmpty()){
            findSupplier.setStatus(false);
            this.supplierRepository.save(findSupplier);
        } else {
            this.supplierRepository.delete(findSupplier);
        }
    }

    @Override
    public PaginationResponse getListSupplier(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1,limit);

        Page supplierPage = this.supplierRepository.findAll(pageable);

        Type listType = new TypeToken<List<SupplierResponse>>(){}.getType();

        List<SupplierResponse> supplierResponses = modelMapper.map(supplierPage.getContent(),listType);

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(supplierResponses);
        paginationResponse.setLastPage(supplierPage.isLast());
        paginationResponse.setPageNumber(supplierPage.getNumber());
        paginationResponse.setPageSize(supplierPage.getSize());
        paginationResponse.setTotalElements(supplierPage.getTotalElements());
        paginationResponse.setTotalPages(supplierPage.getTotalPages());

        return paginationResponse;
    }

    @Override
    public void activeSupplier(String idSupplier) {
        Supplier findSupplier = this.supplierRepository.findById(idSupplier).orElseThrow( () -> new NotFoundException("Mã nhà cung cấp đã không tồn tại"));
        findSupplier.setStatus(true);
        this.supplierRepository.save(findSupplier);
    }

    @Override
    public SupplierResponse findById(String idSupplier) {
        Supplier supplier = this.supplierRepository.findById(idSupplier).orElseThrow(() -> new NotFoundException("Không tìm thấy nhà cung cấp"));
        return this.modelMapper.map(supplier,SupplierResponse.class);
    }
}
