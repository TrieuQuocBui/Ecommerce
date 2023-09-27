package com.bqt.ecommerce.controllers.admin;

import com.bqt.ecommerce.constants.StatusOrderEnum;
import com.bqt.ecommerce.payloads.request.OrderRequest;
import com.bqt.ecommerce.payloads.response.MessResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.services.OrderService;
import com.bqt.ecommerce.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController("Order-Admin")
@CrossOrigin("*")
@RequestMapping("/admin/")
@PreAuthorize("hasAuthority('ADMIN')")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("order/approve")
    public ResponseEntity<PaginationResponse> watchListApprovingOrder(@RequestParam(name = "page", defaultValue = PaginationUtils.DEFAULT_PAGE) int page,
                                                                      @RequestParam(name = "limit", defaultValue = PaginationUtils.DEFAULT_LIMIT) int limit){
        int status = StatusOrderEnum.APPROVE_ORDER.getValue();
        PaginationResponse paginationResponse = orderService.getListApprovingOrder(page,limit,status);
        return ResponseEntity.status(HttpStatus.OK).body(paginationResponse);
    }

    @GetMapping("order/not-approve-yet")
    public ResponseEntity<PaginationResponse> watchListNotApprovingOrder(@RequestParam(name = "page", defaultValue = PaginationUtils.DEFAULT_PAGE) int page,
                                                                      @RequestParam(name = "limit", defaultValue = PaginationUtils.DEFAULT_LIMIT) int limit){
        int status = StatusOrderEnum.NOT_APPROVE_YET_ORDER.getValue();
        PaginationResponse paginationResponse = orderService.getListApprovingOrder(page,limit,status);
        return ResponseEntity.status(HttpStatus.OK).body(paginationResponse);
    }

    @GetMapping("order/disapprove")
    public ResponseEntity<PaginationResponse> watchListDisApprovingOrder(@RequestParam(name = "page", defaultValue = PaginationUtils.DEFAULT_PAGE) int page,
                                                                         @RequestParam(name = "limit", defaultValue = PaginationUtils.DEFAULT_LIMIT) int limit){
        int status = StatusOrderEnum.DISAPPROVE_ORDER.getValue();
        PaginationResponse paginationResponse = orderService.getListApprovingOrder(page,limit,status);
        return ResponseEntity.status(HttpStatus.OK).body(paginationResponse);
    }

    @PutMapping("order/{idOrder}")
    public ResponseEntity<MessResponse> setStatusOrder(@PathVariable("idOrder") Long idOrder, @RequestBody OrderRequest orderRequest){
        this.orderService.updateStatusOrder(idOrder,orderRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Thay đổi thành công"));
    }
}
