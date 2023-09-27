package com.bqt.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String img;

    private boolean status = true;

    @JsonIgnore
    @OneToMany(mappedBy = "brand",fetch = FetchType.LAZY)
    private List<Product> products;
}
