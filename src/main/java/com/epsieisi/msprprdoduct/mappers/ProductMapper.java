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
                product.getProductName().toLowerCase(),
                product.getDescription().toLowerCase(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory().toLowerCase(),
                product.getMark().toLowerCase(),
                product.getCreationDate(),
                product.getLastUpdate(),
                product.getStatus().toLowerCase()
        );
    }

    public  static  Product mapToProduct (ProductDto productDto){
        return new Product(
                productDto.getProductId(),
                productDto.getProductName().toLowerCase(),
                productDto.getDescription().toLowerCase(),
                productDto.getPrice(),
                productDto.getStockQuantity(),
                productDto.getCategory().toLowerCase(),
                productDto.getMark().toLowerCase(),
                productDto.getCreationDate(),
                productDto.getLastUpdate(),
                productDto.getStatus().toLowerCase()
        );
    }
}
