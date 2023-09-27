package com.bqt.ecommerce.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@Table(name = "seri")
public class Seri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date receivingDate;

    private Date saleDate;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
}
