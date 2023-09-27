package com.bqt.ecommerce.controllers.admin;

import com.bqt.ecommerce.entities.ReceiptDetail;
import com.bqt.ecommerce.payloads.request.ReceiptRequest;
import com.bqt.ecommerce.payloads.response.MessResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.services.ReceiptService;
import com.bqt.ecommerce.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/")
@PreAuthorize("hasAuthority('ADMIN')")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("receipt")
    public ResponseEntity<MessResponse> createReceipt(@RequestBody ReceiptRequest receiptRequest){
        this.receiptService.createReceipt(receiptRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessResponse("Tạo phiếu nhập thành công"));
    }

    @GetMapping("receipt")
    public ResponseEntity<PaginationResponse> showListReceipt(@RequestParam(name = "page", defaultValue = PaginationUtils.DEFAULT_PAGE) int page, @RequestParam(name = "limit", defaultValue = PaginationUtils.DEFAULT_LIMIT) int limit){
        PaginationResponse paginationResponse = this.receiptService.getListReceipt(page,limit);
        return ResponseEntity.status(HttpStatus.OK).body(paginationResponse);
    }

    @GetMapping("receipt/{ipReceipt}")
    public ResponseEntity<List<ReceiptDetail>> showReceiptDetails(@PathVariable("ipReceipt") String idReceipt){
        List<ReceiptDetail> receiptDetails = this.receiptService.getListReceiptDetailsByIdReceipt(idReceipt);
        return ResponseEntity.status(HttpStatus.OK).body(receiptDetails);
    }

    @DeleteMapping("receipt/{ipReceipt}")
    public ResponseEntity<MessResponse> removeReceipt(@PathVariable("ipReceipt") String idReceipt){
        this.receiptService.removeReceipt(idReceipt);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Xoá phiếu nhập thành công"));
    }

}
