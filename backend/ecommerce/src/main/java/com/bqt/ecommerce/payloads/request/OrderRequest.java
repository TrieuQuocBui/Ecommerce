package com.bqt.ecommerce.payloads.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {

    private String province;

    private String district;

    private String ward;

    private String addressHome;

    private String note;

    private int status;

    private List<DetailsOrderRequest> detailsOrders;
}
