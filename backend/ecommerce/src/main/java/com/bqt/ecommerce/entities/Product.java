package com.bqt.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String img;

    private String name;

    private double price;

    private String description;

    private boolean status = true;

    @JsonIgnore
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private List<Seri> seris;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_config")
    private Config config;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<DetailsOrder> detailsOrders;

}
