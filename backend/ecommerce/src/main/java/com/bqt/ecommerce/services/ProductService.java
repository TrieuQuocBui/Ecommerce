package com.bqt.ecommerce.services;

import com.bqt.ecommerce.entities.Product;
import com.bqt.ecommerce.payloads.request.ProductRequest;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.payloads.response.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    PaginationResponse getListProduct(int page, int limit, Optional<Long> brandId,String ram,String cpu,String displaySize,String graphicCard,String operatingSystem,String weight,String hardDrive,String size);

    ProductResponse getById(Long id);

    Product[] getProductsForCompare(String nameP, String nameP2);

    List<Product> getProductsNameContaining(String name);

    Product getProductByName(String name);

    void removeProduct(Long idProduct);

    void updateProduct(Long idProduct, String fileName, ProductRequest productRequest);

    void createProduct(String fileName, ProductRequest productRequest);

    PaginationResponse getListProduct(int page, int limit);

    ProductResponse getProductById(Long idProduct);
}
