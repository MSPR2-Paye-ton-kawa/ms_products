package com.epsieisi.msprprdoduct.services;

import com.epsieisi.msprprdoduct.dto.ProductDto;
import com.epsieisi.msprprdoduct.exceptions.ResourceNotFoundException;
import com.epsieisi.msprprdoduct.mappers.ProductMapper;
import com.epsieisi.msprprdoduct.models.Product;
import com.epsieisi.msprprdoduct.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> ProductMapper.mapToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto);
        return ProductMapper.mapToProductDto(productRepository.save(product));
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not exist with given id: "+ productId)
        );
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not exist with given id: "+ productId)
        );
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStockQuantity(productDto.getStockQuantity());
        product.setCategory(productDto.getCategory());
        product.setMark(productDto.getMark());
        product.setCreationDate(productDto.getCreationDate());
        product.setLastUpdate(productDto.getLastUpdate());
        product.setStatus(productDto.getStatus());
        return ProductMapper.mapToProductDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not exist with given id: "+ productId)
        );
        productRepository.deleteById(productId);
    }


}
