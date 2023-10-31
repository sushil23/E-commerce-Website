package com.sushil.ecommerceproject.services;

import com.sushil.ecommerceproject.clients.fakestore.FakeStoreClient;
import com.sushil.ecommerceproject.clients.fakestore.FakeStoreProductResponseDto;
import com.sushil.ecommerceproject.models.Category;
import com.sushil.ecommerceproject.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreCategoryServiceImplementation implements CategoryService {
    private final FakeStoreClient fakeStoreClient;
    public FakeStoreCategoryServiceImplementation(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }
    @Override
    public Optional<Category[]> getAllCategories() {
        Optional<String[]> categoriesOptional = fakeStoreClient.getAllCategories();
        if (categoriesOptional.isEmpty()) {
            return Optional.empty();
        }

        String[] categoryNames = categoriesOptional.get();
        Category[] categories = new Category[categoryNames.length];
        int i = 0;
        for (String categoryName: categoryNames) {
            Category category = new Category();
            category.setName(categoryName);
            categories[i++] = category;
        }
        return Optional.of(categories);
    }

    @Override
    public Optional<List<Product>> getAllProductsInACategory(String categoryName) {
        Optional<List<FakeStoreProductResponseDto>> fakeStoreProdListOptional = fakeStoreClient.getAllProductsInACategory(categoryName);
        if (fakeStoreProdListOptional.isEmpty()) {
            return Optional.empty();
        }

        List<FakeStoreProductResponseDto> fakeStoreProductDtoList = fakeStoreProdListOptional.get();
        List<Product> productList = new ArrayList<>();
        for (FakeStoreProductResponseDto fakeStoreProductResponseDto: fakeStoreProductDtoList) {
            productList.add(FakeStoreProductServiceImplementation.convertFakeStoreProductResponseDtoToProduct(fakeStoreProductResponseDto));
        }

        return Optional.of(productList);
    }
}
