package com.bqt.ecommerce.payloads.response;

import com.bqt.ecommerce.entities.ReceiptDetail;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ReceiptResponse {
    private String id;

    private Date date;

    private List<ReceiptDetailResponse> listReceiptDetail = new ArrayList<>();
}
