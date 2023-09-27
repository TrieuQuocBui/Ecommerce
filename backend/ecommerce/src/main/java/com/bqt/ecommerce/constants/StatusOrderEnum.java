package com.bqt.ecommerce.constants;

import lombok.Getter;

@Getter
public enum StatusOrderEnum {

    APPROVE_ORDER(1),
    DISAPPROVE_ORDER(2),
    NOT_APPROVE_YET_ORDER(0);

    private int value;

    StatusOrderEnum(int value) {
        this.value = value;
    }





}
