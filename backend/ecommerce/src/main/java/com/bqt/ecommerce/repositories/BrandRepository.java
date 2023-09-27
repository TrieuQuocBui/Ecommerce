package com.bqt.ecommerce.repositories;

import com.bqt.ecommerce.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand,Long> {
    List<Brand> findByStatus(boolean status);
}
