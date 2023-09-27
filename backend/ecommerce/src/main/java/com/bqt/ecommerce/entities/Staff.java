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
@Setter
@Getter
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String identification;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private Date birthDay;

    private String image = "default.png";

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staff")
    private List<Order> orders = new ArrayList<>();

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
