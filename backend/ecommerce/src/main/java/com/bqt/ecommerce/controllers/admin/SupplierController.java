package com.bqt.ecommerce.controllers.admin;

import com.bqt.ecommerce.entities.Supplier;
import com.bqt.ecommerce.payloads.request.SupplierRequest;
import com.bqt.ecommerce.payloads.response.MessResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.payloads.response.SupplierResponse;
import com.bqt.ecommerce.services.SupplierService;
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
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("supplier")
    public ResponseEntity<MessResponse> createSupplier(@RequestBody SupplierRequest supplier) {
        this.supplierService.createSupplier(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessResponse("Tạo nhà cung cấp thành công"));
    }

    @PutMapping("supplier/active/{idSupplier}")
    public ResponseEntity<MessResponse> switchOnSupplier(@PathVariable(name = "idSupplier") String idSupplier) {
        this.supplierService.activeSupplier(idSupplier);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Cập nhật nhà cung cấp thành công"));
    }

    @GetMapping("supplier/{idSupplier}")
    public ResponseEntity<SupplierResponse> updateSupplier(@PathVariable(name = "idSupplier") String idSupplier) {
        SupplierResponse supplierResponse = this.supplierService.findById(idSupplier);
        return ResponseEntity.status(HttpStatus.OK).body(supplierResponse);
    }

    @PutMapping("supplier/{idSupplier}")
    public ResponseEntity<MessResponse> updateSupplier(@PathVariable(name = "idSupplier") String idSupplier, @RequestBody SupplierRequest supplierRequest) {
        this.supplierService.updateSupplier(idSupplier, supplierRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Cập nhật nhà cung cấp thành công"));
    }

    @DeleteMapping("supplier/{idSupplier}")
    public ResponseEntity<MessResponse> removeSupplier(@PathVariable(name = "idSupplier") String idSupplier) {
        this.supplierService.removeSupplier(idSupplier);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Xoá nhà cung cấp thành công"));
    }

    @GetMapping("supplier")
    public ResponseEntity<PaginationResponse> displaySuppliers(@RequestParam(name = "page", defaultValue = PaginationUtils.DEFAULT_PAGE) int page, @RequestParam(name = "limit", defaultValue = PaginationUtils.DEFAULT_LIMIT) int limit) {
        PaginationResponse listSupplier = this.supplierService.getListSupplier(page, limit);
        return ResponseEntity.status(HttpStatus.OK).body(listSupplier);
    }
}
