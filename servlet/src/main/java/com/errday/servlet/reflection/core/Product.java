package com.errday.servlet.reflection.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {

    private Long id;
    private String name;
    private int price;

    public int discount(int discountAmount) {
        return price - discountAmount;
    }
}
