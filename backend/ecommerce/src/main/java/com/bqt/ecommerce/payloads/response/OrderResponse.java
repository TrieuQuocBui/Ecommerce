package com.bqt.ecommerce.payloads.response;

import com.bqt.ecommerce.entities.DetailsOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderResponse {
    private long id;

    private Date orderDay;

    private double totalAmount;

    private int status;

    private String addressHome;

    private String province;

    private String district;

    private String ward;

    private String note;

    private List<DetailsOrderResponse> detailsOrders;
}
