package com.bqt.ecommerce.repositories;

import com.bqt.ecommerce.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,String> {
}
