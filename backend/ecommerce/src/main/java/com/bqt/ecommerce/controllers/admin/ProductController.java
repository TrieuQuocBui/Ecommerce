package com.bqt.ecommerce.controllers.admin;

import com.bqt.ecommerce.payloads.request.ProductRequest;
import com.bqt.ecommerce.payloads.response.MessResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.payloads.response.ProductResponse;
import com.bqt.ecommerce.services.FileService;
import com.bqt.ecommerce.services.ProductService;
import com.bqt.ecommerce.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController("Product-Admin")
@CrossOrigin("*")
@RequestMapping("/admin/")
@PreAuthorize("hasAuthority('ADMIN')")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    @Value("${spring.resources.static-locations}")
    private String path;

    @GetMapping("product")
    public ResponseEntity<PaginationResponse> getProductList(@RequestParam(name = "page", defaultValue = PaginationUtils.DEFAULT_PAGE) int page, @RequestParam(name = "limit", defaultValue = PaginationUtils.DEFAULT_LIMIT) int limit) {
        PaginationResponse pageProduct = this.productService.getListProduct(page, limit);
        return ResponseEntity.status(HttpStatus.OK).body(pageProduct);
    }

    @PostMapping("product")
    public ResponseEntity<MessResponse> createProduct(@RequestPart("image") MultipartFile file, @RequestPart("json") ProductRequest productRequest) throws IOException {
        String fileName = this.fileService.uploadImage(path, file);
        this.productService.createProduct(fileName, productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Tạo sản phẩm thành công"));
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<ProductResponse> findProduct(@PathVariable("productId") Long idProduct) {
        ProductResponse productResponse = this.productService.getProductById(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    @PutMapping("product/{productId}/upload")
    public ResponseEntity<MessResponse> updateImageProduct(@PathVariable("productId") Long productId, @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = this.fileService.uploadImage(path,file);
        this.productService.updateProduct(productId,fileName, null);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Chỉnh sữa thương hiệu thành công"));
    }

    @PutMapping("product/{idProduct}")
    public ResponseEntity<MessResponse> updateProduct(@PathVariable("idProduct") Long idProduct, @RequestBody ProductRequest productRequest) throws IOException {
        this.productService.updateProduct(idProduct, null, productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Chỉnh sửa sản phẩm thành công"));
    }

    @DeleteMapping("product/{idProduct}")
    public ResponseEntity<MessResponse> removeProduct(@PathVariable("idProduct") Long idProduct) {
        this.productService.removeProduct(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Xoá sản phẩm thành công"));
    }
}
