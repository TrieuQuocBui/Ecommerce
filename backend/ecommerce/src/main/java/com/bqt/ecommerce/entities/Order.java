package com.bqt.ecommerce.entities;

import com.bqt.ecommerce.constants.StatusOrderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Range;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date orderDay;

    private double totalAmount;

    private int status = StatusOrderEnum.NOT_APPROVE_YET_ORDER.ordinal();

    private String addressHome;

    private String province;

    private String district;

    private String ward;

    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order",fetch = FetchType.EAGER)
    private List<DetailsOrder> detailsOrders;


    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

}
