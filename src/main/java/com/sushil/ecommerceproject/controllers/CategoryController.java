package com.sushil.ecommerceproject.controllers;

import com.sushil.ecommerceproject.dtos.ProductResponseDto;
import com.sushil.ecommerceproject.exceptions.NotFoundException;
import com.sushil.ecommerceproject.models.Product;
import com.sushil.ecommerceproject.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/categories")
    public ResponseEntity<String[]> getAllCategories() throws NotFoundException {
        Optional<String[]> categoriesOptional = categoryService.getAllCategories();
        if (categoriesOptional.isEmpty()) {
            throw new NotFoundException("Categories not found");
        }

        return new ResponseEntity<>(categoriesOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsInACategory(@PathVariable("categoryName") String categoryName) throws NotFoundException {
        Optional<List<Product>> productsOptional = categoryService.getAllProductsInACategory(categoryName);
        if (productsOptional.isEmpty()) {
            throw new NotFoundException("No products found in " + categoryName + " category");
        }

        List<Product> productsList = productsOptional.get();
        List<ProductResponseDto> products = new ArrayList<>();
        for (Product product: productsList) {
            products.add(ProductController.getProductResponseDtoFromProduct(product));
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
