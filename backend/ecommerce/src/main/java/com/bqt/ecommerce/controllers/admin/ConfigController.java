package com.bqt.ecommerce.controllers.admin;

import com.bqt.ecommerce.payloads.request.ConfigRequest;
import com.bqt.ecommerce.payloads.response.BrandResponse;
import com.bqt.ecommerce.payloads.response.ConfigResponse;
import com.bqt.ecommerce.payloads.response.MessResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.services.ConfigService;
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
public class ConfigController {
    @Autowired
    private ConfigService configService;

    @PostMapping("config")
    public ResponseEntity<MessResponse> createSupplier(@RequestBody ConfigRequest configRequest) {
        this.configService.createConfig(configRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessResponse("Tạo cấu hình thành công"));
    }

//    @PutMapping("config/active/{idConfig}")
//    public ResponseEntity<MessResponse> switchOnSupplier(@PathVariable(name = "idConfig") Long idConfig) {
//        this.configService.activeConfig(idConfig);
//        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Cập nhật cấu hình thành công"));
//    }

    @PutMapping("config/{idConfig}")
    public ResponseEntity<MessResponse> updateSupplier(@PathVariable(name = "idConfig") Long idConfig, @RequestBody ConfigRequest configRequest) {
        this.configService.updateConfig(idConfig, configRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Cập nhật cấu hình thành công"));
    }

    @DeleteMapping("config/{idConfig}")
    public ResponseEntity<MessResponse> removeSupplier(@PathVariable(name = "idConfig") Long idConfig) {
        this.configService.removeConfig(idConfig);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Xoá cấu hình thành công"));
    }

    @GetMapping("config")
    public ResponseEntity<PaginationResponse> displaySuppliers(@RequestParam(name = "page", defaultValue = PaginationUtils.DEFAULT_PAGE) int page, @RequestParam(name = "limit", defaultValue = PaginationUtils.DEFAULT_LIMIT) int limit) {
        PaginationResponse listSupplier = this.configService.getListConfig(page, limit);
        return ResponseEntity.status(HttpStatus.OK).body(listSupplier);
    }

    @GetMapping("config/{idConfig}")
    public ResponseEntity<ConfigResponse> findConfig(@PathVariable(name = "idConfig") Long idConfig) {
        ConfigResponse configResponse = this.configService.findById(idConfig);
        return ResponseEntity.status(HttpStatus.OK).body(configResponse);
    }

    @GetMapping("config/all")
    public ResponseEntity<List<ConfigResponse>> showAllConfig() {
        List<ConfigResponse> listConfig = this.configService.getAllConfig();
        return ResponseEntity.status(HttpStatus.OK).body(listConfig);
    }
}
