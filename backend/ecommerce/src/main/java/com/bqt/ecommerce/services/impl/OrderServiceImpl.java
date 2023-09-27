package com.bqt.ecommerce.services.impl;

import com.bqt.ecommerce.entities.Account;
import com.bqt.ecommerce.entities.DetailsOrder;
import com.bqt.ecommerce.entities.Order;
import com.bqt.ecommerce.entities.User;
import com.bqt.ecommerce.exceptions.NotFoundException;
import com.bqt.ecommerce.payloads.request.DetailsOrderRequest;
import com.bqt.ecommerce.payloads.request.OrderRequest;
import com.bqt.ecommerce.payloads.response.AccountResponse;
import com.bqt.ecommerce.payloads.response.OrderResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.repositories.DetailsOrderRepository;
import com.bqt.ecommerce.repositories.OrderRepository;
import com.bqt.ecommerce.services.OrderService;
import com.bqt.ecommerce.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DetailsOrderRepository detailsOrderRepository;


    @Override
    public void createOrder(OrderRequest orderRequest) {

        double totalPayment = orderRequest.getDetailsOrders().stream().reduce(0.0, (sum,obj) -> sum + (obj.getPrice() * obj.getQuantity()),Double::sum);

        User user =  SecurityUtils.getPrincipal().getUser();

        Order order = this.modelMapper.map(orderRequest,Order.class);
        order.setTotalAmount(totalPayment);
        order.setOrderDay(new Date());
        order.setUser(user);

        orderRepository.save(order);

        for (DetailsOrderRequest dor : orderRequest.getDetailsOrders()){
            DetailsOrder detailsOrder = modelMapper.map(dor, DetailsOrder.class);
            detailsOrder.setOrder(order);
            detailsOrderRepository.save(detailsOrder);
        }
    }

    @Override
    public List<OrderResponse> getOrderList(int page, int limit, String dir) {
        Sort sort = dir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by("orderDay").ascending(): Sort.by("orderDay").descending();

        Pageable pageable = PageRequest.of(page - 1, limit,sort);

        User user =  SecurityUtils.getPrincipal().getUser();

        List<Order> orders = this.orderRepository.findByUser(user,pageable).getContent();

        Type listType = new TypeToken<List<OrderResponse>>(){}.getType();

        List<OrderResponse> orderResponses = modelMapper.map(orders,listType);

        return orderResponses;
    }

    @Override
    public void finishOrder(Long idOrder, OrderRequest orderRequest) {
        Order order = this.orderRepository.findById(idOrder).orElseThrow( () -> new NotFoundException("Đơn đặt hàng không tồn tại"));
        order.setStatus(orderRequest.getStatus());
        this.orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long idOrder) {
        Order order = this.orderRepository.findById(idOrder).orElseThrow( () -> new NotFoundException("Đơn đặt hàng không tồn tại"));
        if ( order.getStatus() > 1 ){
            throw new NotFoundException("Không thể huỷ đơn hàng");
        }
        this.orderRepository.delete(order);
    }

    @Override
    public PaginationResponse getListApprovingOrder(int page, int limit, int status) {
        Pageable pageable = PageRequest.of(page -1 ,limit);
        Page orderPage = this.orderRepository.findAllByStatus(pageable,status);

        List<Order> pageContent = orderPage.getContent();

        Type listType = new TypeToken<List<OrderResponse>>(){}.getType();

        List<OrderResponse> orderResponses = modelMapper.map(pageContent,listType);

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(orderResponses);
        paginationResponse.setLastPage(orderPage.isLast());
        paginationResponse.setPageNumber(orderPage.getNumber());
        paginationResponse.setPageSize(orderPage.getSize());
        paginationResponse.setTotalElements(orderPage.getTotalElements());
        paginationResponse.setTotalPages(orderPage.getTotalPages());

        return paginationResponse;

    }

    @Override
    public void updateStatusOrder(Long idOrder, OrderRequest orderRequest) {
        Order order = this.orderRepository.findById(idOrder).orElseThrow(() -> new NotFoundException("Không tìm thấy đơn đặt hàng"));
        order.setStatus(orderRequest.getStatus());
        this.orderRepository.save(order);
    }

}
