package com.sushil.ecommerceproject.services;

import com.sushil.ecommerceproject.dtos.ProductResponseDto;
import com.sushil.ecommerceproject.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<List<Product>> getAllProducts();
    Optional<Product> getASingleProduct(Long productId);
    Optional<Product> addANewProduct(Product product);
    Optional<Product> updateAProduct(Long productId, Product product);
    Optional<Long> deleteAProduct(Long productId);
}
