package com.bqt.ecommerce.repositories;

import com.bqt.ecommerce.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByUsername(String username);
    Boolean existsByUsername(String username);

    Page findByUserNotNull(Pageable pageable);

    Page findByStaffNotNull(Pageable pageable);
}
