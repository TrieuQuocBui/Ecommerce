package com.bqt.ecommerce.payloads.response;

import lombok.Data;

@Data
public class DetailsOrderResponse {

    private long id;

    private int quantity;

    private double price;
}
