package com.epsieisi.msprprdoduct.controllers;


import com.epsieisi.msprprdoduct.dto.ProductDto;
import com.epsieisi.msprprdoduct.models.Product;
import com.epsieisi.msprprdoduct.services.ProductService;
import com.epsieisi.msprprdoduct.services.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.EmptyReaderEventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }


    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);
        productDto.setCreationDate(timestamp);
        productDto.setLastUpdate(timestamp);
        ProductDto savedProduct = productService.createProduct(productDto);
        return  new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId){
        ProductDto productDto =productService.getProductById(productId);
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long productId,
                                                                         @RequestBody ProductDto updatedProduct){
        ProductDto productDto = productService.updateProduct(productId, updatedProduct);
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @PutMapping("/{id}/{quantity}")
    public ResponseEntity<ProductDto> decrementStock(@PathVariable("id") Long productId,@PathVariable("quantity") Integer askedQuantity) {
        ProductDto productDto = productService.decrementStock(productId, askedQuantity);
        return ResponseEntity.ok(productDto);
    }
}
