package com.bqt.ecommerce.specifications;

import com.bqt.ecommerce.entities.Brand;
import com.bqt.ecommerce.entities.Config;
import com.bqt.ecommerce.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ProductSpecification implements Specification<Product> {
    private final Config config;

    private final Brand brand;

    public ProductSpecification(Config config,Brand brand){
        this.config = config;
        this.brand = brand;
    }


    // root is object represent for table
    // query is a query statement
    // criteriaBuilder is object create CriteriaQuery and Operation, Condition,...
    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        //Predicate represent a check function:  Operation and (conjunction), or (disjunction),...
        Predicate predicate = criteriaBuilder.conjunction();

        // Join two tables with together
        Join<Product,Config> configJoin = root.join("config");

        // concat condition together
        if (config.getRam() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(configJoin.get("ram"), config.getRam()));
        }
        if (config.getCpu() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(configJoin.get("cpu"), config.getCpu()));
        }
        if (config.getDisplaySize() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(configJoin.get("displaySize"), config.getDisplaySize()));
        }
        if (config.getGraphicCard() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(configJoin.get("graphicCard"), config.getGraphicCard()));
        }
        if (config.getOperatingSystem() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(configJoin.get("operatingSystem"), config.getOperatingSystem()));
        }
        if (config.getWeight() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(configJoin.get("weight"), config.getWeight()));
        }
        if (config.getHardDrive() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(configJoin.get("hardDrive"), config.getHardDrive()));
        }
        if (config.getSize() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(configJoin.get("size"), config.getSize()));
        }
        if (brand != null){
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("brand"),brand));
        }
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"),true));

        return predicate;
    }

}
