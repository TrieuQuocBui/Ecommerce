package com.bqt.ecommerce.repositories;

import com.bqt.ecommerce.entities.Seri;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SeriRepository extends JpaRepository<Seri,Long> {
    List<Seri> findByReceivingDate(Date date);
}
