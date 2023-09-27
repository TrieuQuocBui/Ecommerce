package com.bqt.ecommerce.payloads.response;

import lombok.Data;

@Data
public class ReceiptDetailResponse {

    private int quantity;

    private double price;

    private ProductResponse product;
}
