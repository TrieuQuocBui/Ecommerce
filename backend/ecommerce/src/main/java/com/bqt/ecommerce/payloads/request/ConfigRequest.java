package com.bqt.ecommerce.payloads.request;

import lombok.Data;

@Data
public class ConfigRequest {
    private String ram;

    private String cpu;

    private String nameConfig;

    private String displaySize;

    private String graphicCard;

    private String operatingSystem;

    private String weight;

    private String madeIn;

    private String hardDrive;

    private String size;

    private int madeYear;
}
