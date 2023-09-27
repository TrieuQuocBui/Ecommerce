package com.bqt.ecommerce.services;

import com.bqt.ecommerce.entities.ReceiptDetail;
import com.bqt.ecommerce.payloads.request.ReceiptRequest;
import com.bqt.ecommerce.payloads.response.PaginationResponse;

import java.util.List;

public interface ReceiptService {
    void createReceipt(ReceiptRequest receiptRequest);

    void removeReceipt(String idReceipt);

    PaginationResponse getListReceipt(int page, int limit);

    List<ReceiptDetail> getListReceiptDetailsByIdReceipt(String idReceipt);
}
