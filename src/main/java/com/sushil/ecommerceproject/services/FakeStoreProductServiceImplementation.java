package com.sushil.ecommerceproject.services;

import com.sushil.ecommerceproject.clients.fakestore.FakeStoreClient;
import com.sushil.ecommerceproject.clients.fakestore.FakeStoreProductResponseDto;
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

    private Product convertFakeStoreProductResponseDtoToProduct(FakeStoreProductResponseDto fakeStoreProductResponseDto) {
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
    public String addANewProduct() {
        return null;
    }

    @Override
    public String updateAProduct() {
        return null;
    }

    @Override
    public String deleteAProduct() {
        return null;
    }
}
