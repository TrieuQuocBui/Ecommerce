package com.bqt.ecommerce.payloads.request;

import com.bqt.ecommerce.payloads.response.ReceiptResponse;
import lombok.Data;

import java.util.List;

@Data
public class SupplierRequest {
        private String id;

        private String name;

        private String sdt;

        private String address;

        private boolean status = true;

        private List<ReceiptResponse> receiptResponses;
}
