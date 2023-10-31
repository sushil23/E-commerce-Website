package com.sushil.ecommerceproject.services;

import com.sushil.ecommerceproject.models.Category;
import com.sushil.ecommerceproject.models.Product;
import com.sushil.ecommerceproject.repositories.CategoryRepository;
import com.sushil.ecommerceproject.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SelfCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    public SelfCategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public Optional<Category[]> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        Category[] categories = new Category[categoryList.size()];
        categoryList.toArray(categories);

        return Optional.of(categories);
    }

    @Override
    public Optional<List<Product>> getAllProductsInACategory(String categoryName) {
        List<Product> products = productRepository.findByCategoryName(categoryName);
        return Optional.of(products);
    }
}
