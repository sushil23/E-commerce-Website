package com.sushil.ecommerceproject.services;

import com.sushil.ecommerceproject.dtos.FakeStoreProductResponseDto;
import com.sushil.ecommerceproject.dtos.ProductResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductServiceImplementation implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreProductServiceImplementation(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public String getAllProducts() {
        return null;
    }

    @Override
    public ProductResponseDto getASingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{productId}",
                FakeStoreProductResponseDto.class,
                productId
        );

        FakeStoreProductResponseDto fakeStoreProductResponseDto = response.getBody();

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(fakeStoreProductResponseDto.getId());
        productResponseDto.setName(fakeStoreProductResponseDto.getTitle());
        productResponseDto.setDescription(fakeStoreProductResponseDto.getDescription());
        productResponseDto.setPrice(fakeStoreProductResponseDto.getPrice());
        productResponseDto.setCategory(fakeStoreProductResponseDto.getCategory());
        productResponseDto.setImage(fakeStoreProductResponseDto.getImage());

        return productResponseDto;
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
