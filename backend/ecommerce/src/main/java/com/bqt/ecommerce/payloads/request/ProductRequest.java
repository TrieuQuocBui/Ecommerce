package com.bqt.ecommerce.payloads.request;

import com.bqt.ecommerce.entities.Brand;
import com.bqt.ecommerce.entities.Config;
import com.bqt.ecommerce.entities.DetailsOrder;
import com.bqt.ecommerce.entities.Seri;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {
    private String img;

    private String name;

    private double price;

    private String description;

    private boolean status;

    private Config config;

    private Brand brand;

}
