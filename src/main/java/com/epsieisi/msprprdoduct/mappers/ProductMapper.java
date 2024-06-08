package com.epsieisi.msprprdoduct.mappers;

import com.epsieisi.msprprdoduct.dto.ProductDto;
import com.epsieisi.msprprdoduct.models.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductMapper {

    public  static ProductDto mapToProductDto(Product product){
        return new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory(),
                product.getMark(),
                product.getCreationDate(),
                product.getLastUpdate(),
                product.getStatus()
        );
    }

    public  static  Product mapToProduct (ProductDto productDto){
        return new Product(
                productDto.getProductId(),
                productDto.getProductName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getStockQuantity(),
                productDto.getCategory(),
                productDto.getMark(),
                productDto.getCreationDate(),
                productDto.getLastUpdate(),
                productDto.getStatus()
        );
    }
}
