package com.sushil.ecommerceproject.services;

import com.sushil.ecommerceproject.clients.fakestore.FakeStoreClient;
import com.sushil.ecommerceproject.clients.fakestore.FakeStoreProductRequestDto;
import com.sushil.ecommerceproject.clients.fakestore.FakeStoreProductResponseDto;
import com.sushil.ecommerceproject.controllers.ProductController;
import com.sushil.ecommerceproject.dtos.ProductResponseDto;
import com.sushil.ecommerceproject.models.Category;
import com.sushil.ecommerceproject.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductServiceImplementation implements ProductService {
    private final RestTemplateBuilder restTemplateBuilder;
    private final FakeStoreClient fakeStoreClient;
    public FakeStoreProductServiceImplementation(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }

    private FakeStoreProductRequestDto getFakeStoreProductReqDtoFromProd(Product product) {
        FakeStoreProductRequestDto fakeStoreProductRequestDto = new FakeStoreProductRequestDto();

        fakeStoreProductRequestDto.setTitle(product.getName());
        fakeStoreProductRequestDto.setDescription(product.getDescription());
        fakeStoreProductRequestDto.setPrice(product.getPrice());
        fakeStoreProductRequestDto.setCategory(product.getCategory().getName());
        fakeStoreProductRequestDto.setImage(product.getImageUrl());

        return fakeStoreProductRequestDto;
    }

     static Product convertFakeStoreProductResponseDtoToProduct(FakeStoreProductResponseDto fakeStoreProductResponseDto) {
        Product product = new Product();

        product.setId(fakeStoreProductResponseDto.getId());
        product.setName(fakeStoreProductResponseDto.getTitle());
        product.setDescription(fakeStoreProductResponseDto.getDescription());
        product.setPrice(fakeStoreProductResponseDto.getPrice());
        Category category = new Category();
        category.setName(fakeStoreProductResponseDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(fakeStoreProductResponseDto.getImage());

        return product;
    }
    @Override
    public Optional<List<Product>> getAllProducts() {
        Optional<List<FakeStoreProductResponseDto>> fakeStoreProductResponseOptional = fakeStoreClient.getAllProducts();
        if (fakeStoreProductResponseOptional.isEmpty()) {
            return Optional.empty();
        }

        List<FakeStoreProductResponseDto> fakeStoreProductDtoList = fakeStoreProductResponseOptional.get();
        List<Product> productList = new ArrayList<>();
        for (FakeStoreProductResponseDto fakeStoreProductResponseDto: fakeStoreProductDtoList) {
            productList.add(convertFakeStoreProductResponseDtoToProduct(fakeStoreProductResponseDto));
        }

        return Optional.of(productList);
    }

    @Override
    public Optional<Product> getASingleProduct(Long productId) {
        Optional<FakeStoreProductResponseDto> fakeStoreProductResponseDto = fakeStoreClient.getASingleProduct(productId);

        if (fakeStoreProductResponseDto.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(convertFakeStoreProductResponseDtoToProduct(fakeStoreProductResponseDto.get()));
        // Below is the new syntax needed to be understood
//        return fakeStoreProductResponseDto.map(this::convertFakeStoreProductResponseDtoToProduct);
    }

    @Override
    public Optional<Product> addANewProduct(Product product) {
        FakeStoreProductRequestDto fakeStoreProductRequestDto = getFakeStoreProductReqDtoFromProd(product);
        Optional<FakeStoreProductResponseDto> fakeStoreProductResponseDto = fakeStoreClient.addANewProduct(fakeStoreProductRequestDto);

        if (fakeStoreProductResponseDto.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(convertFakeStoreProductResponseDtoToProduct(fakeStoreProductResponseDto.get()));
    }

    @Override
    public Optional<Product> updateAProduct(Long productId, Product product) {
        FakeStoreProductRequestDto fakeStoreProductRequestDto = getFakeStoreProductReqDtoFromProd(product);
        Optional<FakeStoreProductResponseDto> fakeStoreProdOptional = fakeStoreClient.updateAProduct(productId, fakeStoreProductRequestDto);
        if (fakeStoreProdOptional.isEmpty()) {
            return Optional.empty();
        }

        FakeStoreProductResponseDto fakeStoreProductResponseDto = fakeStoreProdOptional.get();
        Product updatedProduct = convertFakeStoreProductResponseDtoToProduct(fakeStoreProductResponseDto);
        return Optional.of(updatedProduct);
    }

    @Override
    public Optional<Product> deleteAProduct(Long productId) {
        Optional<FakeStoreProductResponseDto> fakeStoreProdOptional = fakeStoreClient.deleteAProduct(productId);
        if (fakeStoreProdOptional.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(convertFakeStoreProductResponseDtoToProduct(fakeStoreProdOptional.get()));
    }
}
