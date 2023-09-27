package com.bqt.ecommerce.services;

import com.bqt.ecommerce.payloads.request.OrderRequest;
import com.bqt.ecommerce.payloads.response.OrderResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;

import java.util.List;

public interface OrderService {
    void createOrder(OrderRequest orderRequest);

    List<OrderResponse> getOrderList(int page, int limit, String dir);

    void finishOrder(Long idOrder, OrderRequest orderRequest);

    void cancelOrder(Long idOrder);

    PaginationResponse getListApprovingOrder(int page, int limit, int status);

    void updateStatusOrder(Long idOrder, OrderRequest orderRequest);
}
