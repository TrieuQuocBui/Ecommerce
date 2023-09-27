package com.bqt.ecommerce.controllers.user;

import com.bqt.ecommerce.entities.Product;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.services.ProductService;
import com.bqt.ecommerce.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class HomeController {

    // @Autowired say spring inject an instance of object into this property when it is init
    @Autowired
    private ProductService productService;

    @GetMapping("home")
    public ResponseEntity<PaginationResponse> showHome(
            @RequestParam(name = "brand", required = false) Long brandId,
            @RequestParam(name = "ram", required = false) String ram,
            @RequestParam(name = "cpu", required = false) String cpu,
            @RequestParam(name = "displaySize", required = false) String displaySize,
            @RequestParam(name = "graphicCard", required = false) String graphicCard,
            @RequestParam(name = "operatingSystem", required = false) String operatingSystem,
            @RequestParam(name = "weight", required = false) String weight,
            @RequestParam(name = "hardDrive", required = false) String hardDrive,
            @RequestParam(name = "size", required = false) String size,
            @RequestParam(name = "page", defaultValue = PaginationUtils.DEFAULT_PAGE) int page,
            @RequestParam(name = "limit", defaultValue = PaginationUtils.DEFAULT_LIMIT) int limit) {
        PaginationResponse productList = productService.getListProduct(page, limit, Optional.ofNullable(brandId),ram,cpu,displaySize,graphicCard,operatingSystem,weight,hardDrive,size);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }
}
