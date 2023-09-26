package com.sushil.ecommerceproject.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String name;
    private String brandName;
    private String description;
    private double price;
    private Category category;
    private String imageUrl;
}
