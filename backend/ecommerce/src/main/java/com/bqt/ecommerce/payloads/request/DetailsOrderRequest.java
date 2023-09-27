package com.bqt.ecommerce.payloads.request;

import com.bqt.ecommerce.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DetailsOrderRequest {

    private Product product;

    private int quantity;

    private double price;
}
