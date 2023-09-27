package com.bqt.ecommerce.repositories;

import com.bqt.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
