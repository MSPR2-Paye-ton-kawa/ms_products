package com.epsieisi.msprprdoduct.services;

import com.epsieisi.msprprdoduct.dto.ProductDto;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto createProduct(ProductDto productDto);

    ProductDto getProductById(Long productId);

    ProductDto updateProduct(Long productId, ProductDto productDto);

    void deleteProduct(Long productId);
}
