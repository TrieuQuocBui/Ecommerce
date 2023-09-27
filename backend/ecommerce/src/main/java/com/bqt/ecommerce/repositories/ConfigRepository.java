package com.bqt.ecommerce.repositories;

import com.bqt.ecommerce.entities.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config,Long> {
}
