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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);
        productDto.setLastUpdate(timestamp);
        productDto.setCreationDate(timestamp);
        Product product = ProductMapper.mapToProduct(productDto);
        return ProductMapper.mapToProductDto(productRepository.save(product));
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("The product doesn't exist in the database")
        );
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("The product doesn't exist in the database")
        );
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStockQuantity(productDto.getStockQuantity());
        product.setCategory(productDto.getCategory());
        product.setMark(productDto.getMark());
        product.setCreationDate(productDto.getCreationDate());
        product.setLastUpdate(timestamp);
        product.setStatus(productDto.getStatus());
        return ProductMapper.mapToProductDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Le  "+ productId)
        );
        productRepository.deleteById(productId);
    }


    @Override
    public Boolean isAvailable  (Long idProduct, Integer askedQuantity) {
        ProductDto productDto = getProductById(idProduct);
        if(productDto != null){
            if (productDto.getStockQuantity() >= askedQuantity)
                return true;
            else
                return false;
        }
        else {
            throw new ResourceNotFoundException("The product doesn't exist in the database");
        }
    }

    @Override
    public ProductDto decrementStock(Long idProduct, Integer askedQuantity) {
        if(isAvailable(idProduct, askedQuantity)){
            ProductDto productDto = getProductById(idProduct);
            productDto.setStockQuantity(productDto.getStockQuantity() - askedQuantity);
            return updateProduct(idProduct,productDto);
        }
        else {
            throw new ResourceNotFoundException("The product doesn't exist in the database");
        }
    }

}
