package com.bqt.ecommerce.repositories;

import com.bqt.ecommerce.entities.ReceiptDetail;
import com.bqt.ecommerce.entities.ReceiptDetailPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsReceiptRepository extends JpaRepository<ReceiptDetail, ReceiptDetailPk> {
}
