package com.bqt.ecommerce.services.impl;

import com.bqt.ecommerce.entities.*;
import com.bqt.ecommerce.exceptions.BadRequestException;
import com.bqt.ecommerce.exceptions.NotFoundException;
import com.bqt.ecommerce.payloads.request.ReceiptDetailsRequest;
import com.bqt.ecommerce.payloads.request.ReceiptRequest;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.repositories.DetailsReceiptRepository;
import com.bqt.ecommerce.repositories.ReceiptRepository;
import com.bqt.ecommerce.repositories.SeriRepository;
import com.bqt.ecommerce.repositories.SupplierRepository;
import com.bqt.ecommerce.services.ReceiptService;
import com.bqt.ecommerce.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private DetailsReceiptRepository detailsReceiptRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SeriRepository seriRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createReceipt(ReceiptRequest receiptRequest) {
        boolean checkReceipt = this.receiptRepository.findById(receiptRequest.getId()).isEmpty();

        if (checkReceipt){
            Receipt receipt = new Receipt();
            receipt.setId(receiptRequest.getId());
            receipt.setDate(receiptRequest.getDate());

            Supplier supplier = this.supplierRepository.findById(receiptRequest.getSupplier().getId()).orElseThrow(() -> new NotFoundException("Không tìm thấy nhà cung cấp"));
            receipt.setSupplier(supplier);

            List<ReceiptDetail> receiptDetailsList = new ArrayList<>();
            for (ReceiptDetailsRequest receiptDetailsRequest : receiptRequest.getListReceiptDetail()){
                ReceiptDetail receiptDetail = new ReceiptDetail();
                ReceiptDetailPk receiptDetailPk = new ReceiptDetailPk();
                receiptDetailPk.setReceipt(receipt.getId());
                receiptDetailPk.setProduct(receiptDetailsRequest.getProduct().getId());
                receiptDetail.setId(receiptDetailPk);
                receiptDetail.setPrice(receiptDetailsRequest.getPrice());
                receiptDetail.setQuantity(receiptDetailsRequest.getQuantity());
                receiptDetail.setReceipt(receipt);
                receiptDetail.setProduct(receiptDetailsRequest.getProduct());
                receiptDetailsList.add(receiptDetail);
            }

            receipt.setListReceiptDetail(receiptDetailsList);

            this.receiptRepository.save(receipt);

            for (ReceiptDetail receiptDetails : receipt.getListReceiptDetail()){
                for (int i = 0; i < receiptDetails.getQuantity(); i++){
                    Seri seri = new Seri();
                    seri.setReceivingDate(receiptRequest.getDate());
                    seri.setProduct(receiptDetails.getProduct());
                    this.seriRepository.save(seri);
                }
            }

        } else {
            throw  new BadRequestException("Mã mã phiếu nhập đã tồn tại");
        }
    }


    @Override
    public void removeReceipt(String idReceipt) {
        Receipt receipt = this.receiptRepository.findById(idReceipt).orElseThrow(() -> new NotFoundException("Phiếu nhập không tồn tại"));

        List<Seri> seris = this.seriRepository.findByReceivingDate(receipt.getDate());

        this.seriRepository.deleteAll(seris);

        for (ReceiptDetail receiptDetail : receipt.getListReceiptDetail()){
            this.detailsReceiptRepository.delete(receiptDetail);
        }

        this.receiptRepository.delete(receipt);

    }

    @Override
    public PaginationResponse getListReceipt(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);

        Page receiptPage = this.receiptRepository.findAll(pageable);

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(receiptPage.getContent());
        paginationResponse.setLastPage(receiptPage.isLast());
        paginationResponse.setPageNumber(receiptPage.getNumber());
        paginationResponse.setPageSize(receiptPage.getSize());
        paginationResponse.setTotalElements(receiptPage.getTotalElements());
        paginationResponse.setTotalPages(receiptPage.getTotalPages());

        return paginationResponse;
    }

    @Override
    public List<ReceiptDetail> getListReceiptDetailsByIdReceipt(String idReceipt) {
        Receipt receipt = this.receiptRepository.findById(idReceipt).orElseThrow(() -> new NotFoundException("Phiếu nhập không tồn tại"));
        List<ReceiptDetail> receiptDetails = receipt.getListReceiptDetail();
        return receiptDetails;
    }
}
