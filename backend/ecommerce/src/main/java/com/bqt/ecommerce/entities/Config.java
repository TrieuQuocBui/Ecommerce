package com.bqt.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@Table(name = "config")
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameConfig;

    private String ram;

    private String cpu;

    private String displaySize;

    private String graphicCard;

    private String operatingSystem;

    private String weight;

    private String madeIn;

    private String hardDrive;

    private String size;

    private int madeYear;

    @JsonIgnore
    @OneToMany(mappedBy = "config",cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
}
