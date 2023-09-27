package com.bqt.ecommerce.repositories;

import com.bqt.ecommerce.entities.Brand;
import com.bqt.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

    Page<Product> findByStatusTrueAndBrand(Brand brand, Pageable pageable);

    Page<Product> findByStatusTrue(Pageable pageable);

    List<Product> findByNameContaining(String name);

    Optional<Product> findByName(String name);
}
