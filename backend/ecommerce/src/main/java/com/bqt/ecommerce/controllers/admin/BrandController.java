package com.bqt.ecommerce.controllers.admin;

import com.bqt.ecommerce.payloads.request.BrandRequest;
import com.bqt.ecommerce.payloads.response.BrandResponse;
import com.bqt.ecommerce.payloads.response.MessResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.services.BrandService;
import com.bqt.ecommerce.services.FileService;
import com.bqt.ecommerce.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController("Controller-Admin")
@CrossOrigin("*")
@RequestMapping("/admin/")
@PreAuthorize("hasAuthority('ADMIN')")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private FileService fileService;

    @Value("${spring.resources.static-locations}")
    private String path;

    @GetMapping("brand/all")
    public ResponseEntity<List<BrandResponse>> showAllBrand() {
        List<BrandResponse> listBrand = this.brandService.getAllBrand();
        return ResponseEntity.status(HttpStatus.OK).body(listBrand);
    }

    @GetMapping("brand")
    public ResponseEntity<PaginationResponse> showBrandList(@RequestParam(name = "page", defaultValue = PaginationUtils.DEFAULT_PAGE) int page, @RequestParam(name = "limit", defaultValue = PaginationUtils.DEFAULT_LIMIT) int limit) {
        PaginationResponse listBrand = this.brandService.getBrandList(page, limit);
        return ResponseEntity.status(HttpStatus.OK).body(listBrand);
    }

    @PostMapping("brand")
    public ResponseEntity<MessResponse> createBrand(@RequestPart("image") MultipartFile file, @RequestPart("json") BrandRequest brandRequest) throws IOException {
        if (file != null) {
            String fileName = this.fileService.uploadImage(path, file);
            this.brandService.createBrand(fileName, brandRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessResponse("Tạo thương hiệu thành công"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessResponse("Lỗi khi tải lên tệp tin"));
        }
    }

    @GetMapping("brand/{idBrand}")
    public ResponseEntity<BrandResponse> findBrand(@PathVariable("idBrand") Long idBrand) throws IOException {
        BrandResponse brandResponse = this.brandService.getBrandById(idBrand);
        return ResponseEntity.status(HttpStatus.CREATED).body(brandResponse);
    }

    @PutMapping("brand/{idBrand}/upload")
    public ResponseEntity<MessResponse> updateBrand(@PathVariable("idBrand") Long idBrand, @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = this.fileService.uploadImage(path,file);
        this.brandService.updateBrand(idBrand,fileName, null);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Chỉnh sữa thương hiệu thành công"));
    }

    @PutMapping("brand/{idBrand}")
    public ResponseEntity<MessResponse> updateBrand(@PathVariable("idBrand") Long idBrand, @RequestBody BrandRequest brandRequest) throws IOException {
        this.brandService.updateBrand(idBrand, null, brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessResponse("Chỉnh sữa thương hiệu thành công"));
    }

    @DeleteMapping("brand/{idBrand}")
    public ResponseEntity<MessResponse> deleteBrand(@PathVariable("idBrand") Long idBrand) {
        this.brandService.removeBrand(idBrand);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessResponse("Xoá thương hiệu thành công"));
    }
}
