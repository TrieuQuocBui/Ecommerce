package com.bqt.ecommerce.controllers.user;

import com.bqt.ecommerce.entities.Product;
import com.bqt.ecommerce.payloads.response.ProductResponse;
import com.bqt.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("product/{idProduct}")
    public ResponseEntity<ProductResponse> showDetailsProduct(@PathVariable("idProduct") Long idProduct){
        ProductResponse product = productService.getById(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("compare")
    public ResponseEntity<Product[]> showCompareProducts(@RequestParam(name = "pc",required = false) String nameP,@RequestParam(name = "p",required = false) String nameP2){
        Product[] productList = productService.getProductsForCompare(nameP,nameP2);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }


    @GetMapping("product/search/{name}")
    public ResponseEntity<Product> showProduct(@PathVariable(name = "name") String name){
        Product product= productService.getProductByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("product/search")
    public ResponseEntity<List<Product>> showSuggestProduct(@RequestParam(name = "name") String name){
        List<Product> products= productService.getProductsNameContaining(name);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
