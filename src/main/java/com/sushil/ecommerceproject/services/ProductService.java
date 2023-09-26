package com.sushil.ecommerceproject.services;

import com.sushil.ecommerceproject.dtos.ProductResponseDto;

public interface ProductService {
    String getAllProducts();
    ProductResponseDto getASingleProduct(Long productId);
    String addANewProduct();
    String updateAProduct();
    String deleteAProduct();
}
