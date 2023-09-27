package com.bqt.ecommerce.payloads.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConfigResponse {
    private Long id;

    private String ram;

    private String nameConfig;

    private String cpu;

    private String displaySize;

    private String graphicCard;

    private String operatingSystem;

    private String weight;

    private String madeIn;

    private String hardDrive;

    private String size;

    private int madeYear;

    private List<ProductResponse> products = new ArrayList<>();
}
