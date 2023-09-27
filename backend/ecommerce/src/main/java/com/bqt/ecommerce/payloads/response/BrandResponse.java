package com.bqt.ecommerce.payloads.response;

import com.bqt.ecommerce.entities.Product;
import lombok.Data;

import java.util.List;

@Data
public class BrandResponse {
    private long id;

    private String name;

    private String img;

    private boolean status;

    private List<ProductResponse> products;
}
