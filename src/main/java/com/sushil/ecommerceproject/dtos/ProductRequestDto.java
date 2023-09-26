package com.sushil.ecommerceproject.dtos;

import com.sushil.ecommerceproject.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String name;
    private String brandName;
    private String description;
    private double price;
    private String category;
    private String image;
}
