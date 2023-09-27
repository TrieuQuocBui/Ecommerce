package com.bqt.ecommerce.controllers.user;

import com.bqt.ecommerce.payloads.response.BrandResponse;
import com.bqt.ecommerce.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("brand")
    public ResponseEntity<List<BrandResponse>> getBrandListActive(){
        List<BrandResponse> brandResponses = this.brandService.getAllActiveBrand(true);
        return ResponseEntity.status(HttpStatus.OK).body(brandResponses);
    }
}
