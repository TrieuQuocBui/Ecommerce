package com.bqt.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Supplier {
    @Id
    @Column(length = 10)
    private String id;

    @Column(length = 50)
    private String name;

    @Column(length = 11)
    private String sdt;

    @Column(length = 100)
    private String address;

    @Column(length = 50)
    private boolean status = true;

    @OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Receipt> receipt;

}
