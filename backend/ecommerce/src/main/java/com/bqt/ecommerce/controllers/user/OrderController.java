package com.bqt.ecommerce.controllers.user;

import com.bqt.ecommerce.entities.Account;
import com.bqt.ecommerce.payloads.request.DetailsOrderRequest;
import com.bqt.ecommerce.payloads.request.OrderRequest;
import com.bqt.ecommerce.payloads.response.MessResponse;
import com.bqt.ecommerce.payloads.response.OrderResponse;
import com.bqt.ecommerce.services.OrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("order")
    public ResponseEntity<MessResponse> orderOfUser(@RequestBody OrderRequest orderRequest){
        this.orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Đặt hàng thành công"));
    }

    @GetMapping("order/list")
    public ResponseEntity<List<OrderResponse>> showOrderList(@RequestParam(name = "page",defaultValue = "1") int page, @RequestParam(name = "limit",defaultValue = "9") int limit,@RequestParam(name = "dir",defaultValue = "asc") String dir){
        List<OrderResponse> list = this.orderService.getOrderList(page,limit,dir);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PutMapping("order/{idOrder}/finish")
    public ResponseEntity<MessResponse> finishOrder(@PathVariable(name = "idOrder") Long idOrder,@RequestBody OrderRequest orderRequest){
        this.orderService.finishOrder(idOrder,orderRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Cảm ơn quý khách đã mua san phẩm"));
    }

    @DeleteMapping("order/{idOrder}/cancel")
    public ResponseEntity<MessResponse> cancelOrder(@PathVariable(name = "idOrder") Long idOrder){
        this.orderService.cancelOrder(idOrder);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Huỷ đơn hàng thành công"));
    }
}
