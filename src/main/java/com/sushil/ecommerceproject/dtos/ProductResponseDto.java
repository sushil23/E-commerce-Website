package com.sushil.ecommerceproject.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private long id;
    private String name;
    private String brandName;
    private String description;
    private double price;
    private String category;
    private String image;
}
