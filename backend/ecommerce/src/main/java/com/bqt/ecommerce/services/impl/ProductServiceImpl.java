package com.bqt.ecommerce.services.impl;

import com.bqt.ecommerce.entities.Brand;
import com.bqt.ecommerce.entities.Config;
import com.bqt.ecommerce.entities.Product;
import com.bqt.ecommerce.exceptions.NotFoundException;
import com.bqt.ecommerce.payloads.request.ProductRequest;
import com.bqt.ecommerce.payloads.response.BrandResponse;
import com.bqt.ecommerce.payloads.response.ConfigResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.payloads.response.ProductResponse;
import com.bqt.ecommerce.services.FileService;
import com.bqt.ecommerce.specifications.ProductSpecification;
import com.bqt.ecommerce.repositories.BrandRepository;
import com.bqt.ecommerce.repositories.ProductRepository;
import com.bqt.ecommerce.services.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private FileService fileService;

    @Value("${spring.resources.static-locations}")
    private String path;



    @Override
    public PaginationResponse getListProduct(int page, int limit, Optional<Long> brandId, String ram, String cpu, String displaySize, String graphicCard, String operatingSystem, String weight, String hardDrive, String size) {

        Pageable pageable = PageRequest.of(page - 1, limit);

        Brand brand = brandId.isPresent() ? brandRepository.findById(brandId.get()).orElseThrow(() -> new NotFoundException("Hãng không tồn tại")) : null;

        Config config = new Config();
        config.setRam(ram);
        config.setCpu(cpu);
        config.setDisplaySize(displaySize);
        config.setGraphicCard(graphicCard);
        config.setHardDrive(hardDrive);
        config.setOperatingSystem(operatingSystem);
        config.setWeight(weight);
        config.setSize(size);

        ProductSpecification configSpecification = new ProductSpecification(config, brand);

        Page productPage = productRepository.findAll(configSpecification, pageable);

        List<Product> products = productPage.getContent();

        Type listType = new TypeToken< List<ProductResponse>>(){}.getType();

        List<ProductResponse> productResponses = this.modelMapper.map(products,listType);

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(productResponses);
        paginationResponse.setLastPage(productPage.isLast());
        paginationResponse.setPageNumber(productPage.getNumber());
        paginationResponse.setPageSize(productPage.getSize());
        paginationResponse.setTotalElements(productPage.getTotalElements());
        paginationResponse.setTotalPages(productPage.getTotalPages());

        return paginationResponse;
    }

    @Override
    public ProductResponse getById(Long id) {
        Product findProduct = productRepository.findById(id).orElseThrow(() -> new NotFoundException("mã " + id + " không tồn tại"));
        return this.modelMapper.map(findProduct,ProductResponse.class);
    }

    @Override
    public Product[] getProductsForCompare(String nameP, String nameP2) {
        Product[] productList = new Product[2];

        if (nameP != null) {
            Product product1 = productRepository.findByName(nameP).orElseThrow(() -> new NotFoundException("Tên " + nameP + " không tồn tại"));
            productList[0] = product1;
        }

        if (nameP2 != null) {
            Product product2 = productRepository.findByName(nameP2).orElseThrow(() -> new NotFoundException("Tên " + nameP2 + " không tồn tại"));
            productList[1] = product2;
        }
        return productList;
    }

    @Override
    public List<Product> getProductsNameContaining(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name).orElseThrow(() -> new NotFoundException("Tên " + name + " không tồn tại"));
    }

    @Override
    public void removeProduct(Long idProduct) {
        Product findProduct = this.productRepository.findById(idProduct).orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm"));
        if (findProduct.getDetailsOrders().isEmpty()) {
            this.productRepository.delete(findProduct);
        } else {
            findProduct.setStatus(false);
            this.productRepository.save(findProduct);
        }
    }

    @Override
    public void updateProduct(Long idProduct, String fileName, ProductRequest productRequest) {
        Product findProduct = this.productRepository.findById(idProduct).orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm"));

        if (productRequest != null){
            this.modelMapper.map(productRequest, findProduct);
            findProduct.setId(idProduct);
        } else {
            this.fileService.deleteImage(path, findProduct.getImg());
            findProduct.setImg(fileName);
        }
        this.productRepository.save(findProduct);
    }

    @Override
    public void createProduct(String fileName, ProductRequest productRequest) {
        Product product = this.modelMapper.map(productRequest, Product.class);
        Brand brand = this.brandRepository.findById(productRequest.getBrand().getId()).orElseThrow(() -> new NotFoundException("Không tìm thấy thương hiệu"));
        product.setImg(fileName);
        brand.getProducts().add(product);
        this.productRepository.save(product);
        this.brandRepository.save(brand);
    }

    @Override
    public PaginationResponse getListProduct(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);

        Page productPage = this.productRepository.findAll(pageable);

        List<Product> products = productPage.getContent();

        Type listType = new TypeToken< List<ProductResponse>>(){}.getType();

        List<ProductResponse> productResponses = this.modelMapper.map(products,listType);

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(productResponses);
        paginationResponse.setLastPage(productPage.isLast());
        paginationResponse.setPageNumber(productPage.getNumber());
        paginationResponse.setPageSize(productPage.getSize());
        paginationResponse.setTotalElements(productPage.getTotalElements());
        paginationResponse.setTotalPages(productPage.getTotalPages());

        return paginationResponse;
    }

    @Override
    public ProductResponse getProductById(Long idProduct) {
        Product product = this.productRepository.findById(idProduct).orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm"));

        return this.modelMapper.map(product,ProductResponse.class);
    }
}
