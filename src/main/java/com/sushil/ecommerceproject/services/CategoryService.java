package com.sushil.ecommerceproject.services;

import com.sushil.ecommerceproject.models.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<String[]> getAllCategories();
    Optional<List<Product>> getAllProductsInACategory(String categoryName);
}
