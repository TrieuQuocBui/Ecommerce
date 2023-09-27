package com.bqt.ecommerce.payloads.response;

import com.bqt.ecommerce.entities.Receipt;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@Data
public class SupplierResponse {
    private String id;

    private String name;

    private String sdt;

    private String address;

    private boolean status;

    private List<ReceiptResponse> receipt;
}
