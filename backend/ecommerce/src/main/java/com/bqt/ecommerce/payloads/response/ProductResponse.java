package com.bqt.ecommerce.payloads.response;

import com.bqt.ecommerce.entities.Brand;
import com.bqt.ecommerce.payloads.BrandPayloads;
import com.bqt.ecommerce.payloads.request.ConfigRequest;
import lombok.Data;

@Data
public class ProductResponse {
    private long id;

    private String img;

    private String name;

    private double price;

    private String description;

    private boolean status;

    private ConfigPayLoad config;

    private Brand brand;
}
