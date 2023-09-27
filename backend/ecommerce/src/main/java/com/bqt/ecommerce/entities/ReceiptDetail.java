package com.bqt.ecommerce.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class ReceiptDetail {
    @EmbeddedId
    private ReceiptDetailPk id;

    @ManyToOne
    @MapsId("receipt")
    @JoinColumn()
    private Receipt receipt;

    @ManyToOne
    @MapsId("product")
    @JoinColumn()
    private Product product;

    @Column()
    private int quantity;

    @Column()
    private double price;
}
