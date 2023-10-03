package com.sushil.ecommerceproject.services;

import com.sushil.ecommerceproject.clients.fakestore.FakeStoreClient;
import com.sushil.ecommerceproject.clients.fakestore.FakeStoreProductResponseDto;
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
    public Optional<String[]> getAllCategories() {
        Optional<String[]> categoriesOptional = fakeStoreClient.getAllCategories();
        if (categoriesOptional.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(categoriesOptional.get());
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
