package com.bqt.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Receipt {
    @Id
    @Column(length = 10)
    private String id;

    @Column
    private Date date;

    @ManyToOne
    @JoinColumn(name ="id_supplier")
    private Supplier supplier;


    @OneToMany(mappedBy = "receipt",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ReceiptDetail> listReceiptDetail = new ArrayList<>();
}
