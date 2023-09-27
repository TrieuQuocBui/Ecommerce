package com.bqt.ecommerce.payloads.request;

import com.bqt.ecommerce.entities.ReceiptDetail;
import com.bqt.ecommerce.entities.Supplier;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReceiptRequest {
    private String id;
    private Date date;
    private SupplierRequest supplier;
    private List<ReceiptDetailsRequest> listReceiptDetail;
}
