package com.bqt.ecommerce.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ReceiptDetailPk implements Serializable {
    @Column(length = 10)
    private String receipt;

    @Column(length = 10)
    private Long product;
}
