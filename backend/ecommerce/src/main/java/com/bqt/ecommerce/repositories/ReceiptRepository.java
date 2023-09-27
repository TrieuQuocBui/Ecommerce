package com.bqt.ecommerce.repositories;

import com.bqt.ecommerce.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt,String> {
}
