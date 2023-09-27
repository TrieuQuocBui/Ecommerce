package com.bqt.ecommerce.controllers.admin;

import com.bqt.ecommerce.entities.Product;
import com.bqt.ecommerce.payloads.response.MessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController("HomeAdmin")
@CrossOrigin("*")
@RequestMapping("/admin/")
@PreAuthorize("hasAuthority('ADMIN')")
public class HomeController {


    @GetMapping("home")
    public ResponseEntity<String> showHomeAdmin() {

        return ResponseEntity.status(HttpStatus.OK).body("Admin xin chào");
    }
}
