package com.bqt.ecommerce.payloads.response;

import lombok.Data;

@Data
public class ConfigPayLoad {
    private long id;

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
