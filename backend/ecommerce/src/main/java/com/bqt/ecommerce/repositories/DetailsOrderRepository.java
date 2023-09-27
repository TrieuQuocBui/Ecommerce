package com.bqt.ecommerce.repositories;

import com.bqt.ecommerce.entities.DetailsOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsOrderRepository extends JpaRepository<DetailsOrder,Long> {
}
