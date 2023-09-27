package com.bqt.ecommerce.payloads.request;

import lombok.Data;

@Data
public class BrandRequest {
    private String name;

    private String img;

    private boolean status = true;
}
