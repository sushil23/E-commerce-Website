package com.sushil.ecommerceproject.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDto {
    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
