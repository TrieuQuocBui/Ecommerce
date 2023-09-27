package com.bqt.ecommerce.repositories;


import com.bqt.ecommerce.entities.Order;
import com.bqt.ecommerce.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Page<Order> findByUser(User user, Pageable pageable);

    Page findAllByStatus(Pageable pageable, int status);
}
