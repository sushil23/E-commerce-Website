package com.sushil.ecommerceproject.services;

import com.sushil.ecommerceproject.models.Product;

import java.util.List;
import java.util.Optional;

public class SelfCategoryService implements CategoryService {
    @Override
    public Optional<String[]> getAllCategories() {
        return Optional.empty();
    }

    @Override
    public Optional<List<Product>> getAllProductsInACategory(String categoryName) {
        return Optional.empty();
    }
}
