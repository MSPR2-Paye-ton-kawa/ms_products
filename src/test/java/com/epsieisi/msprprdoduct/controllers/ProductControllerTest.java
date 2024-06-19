package com.epsieisi.msprprdoduct.controllers;

import com.epsieisi.msprprdoduct.dto.ProductDto;
import com.epsieisi.msprprdoduct.exceptions.ResourceNotFoundException;
import com.epsieisi.msprprdoduct.mappers.ProductMapper;
import com.epsieisi.msprprdoduct.models.Product;
import com.epsieisi.msprprdoduct.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.BodyBuilder.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product product1 = new Product("productName1", "description1", 10f, 10, "category1", "mark1","status1");
        ProductDto productDto = ProductMapper.mapToProductDto(product1);
        productDto.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        productDto.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

        when(productService.createProduct(any(ProductDto.class))).thenReturn(productDto);

        String productJson = objectMapper.writeValueAsString(productDto);


        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productName").value("productName1"))
                .andExpect(jsonPath("$.description").value("description1"))
                .andExpect(jsonPath("$.price").value(10.0));
    }

    @Test
    public void testGetProductById() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setStockQuantity(10);
        productDto.setProductName("productName");
        productDto.setDescription("description");
        productDto.setPrice(30f);
        productDto.setCategory("category");
        productDto.setMark("mark");
        productDto.setStatus("status");

        when(productService.getProductById(1L)).thenReturn(productDto);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.price").value(30.0));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        ProductDto originalProductDto = new ProductDto();
        originalProductDto.setProductId(1L);
        originalProductDto.setStockQuantity(10);
        originalProductDto.setProductName("productName");
        originalProductDto.setDescription("description");
        originalProductDto.setPrice(30f);
        originalProductDto.setCategory("category");
        originalProductDto.setMark("mark");
        originalProductDto.setStatus("status");

        // Crée un ProductDto mis à jour pour simuler la réponse du service
        ProductDto updatedProductDto = new ProductDto();
        updatedProductDto.setProductId(1L);
        updatedProductDto.setStockQuantity(10);
        updatedProductDto.setProductName("Updated Product");
        updatedProductDto.setDescription("Updated Description");
        updatedProductDto.setPrice(20.0f);
        updatedProductDto.setCategory("category");
        updatedProductDto.setMark("mark");
        updatedProductDto.setStatus("status");

        // Configure le mock pour retourner le produit mis à jour
        when(productService.updateProduct(any(Long.class), any(ProductDto.class))).thenReturn(updatedProductDto);

        // Effectue la requête et vérifie les résultats
        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productName\": \"Updated Product\", \"description\": \"Updated Description\", \"price\": 20.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.price").value(20.0))
                .andExpect(jsonPath("$.productName").value("Updated Product"));
    }


    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product deleted successfully"));
    }

    @Test
    public void testDecrementStock() throws Exception {
        // Crée un ProductDto avec une quantité de stock initiale
        ProductDto initialProductDto = new ProductDto();
        initialProductDto.setProductId(1L);
        initialProductDto.setStockQuantity(10);
        initialProductDto.setProductName("productName");
        initialProductDto.setDescription("description");
        initialProductDto.setPrice(30f);
        initialProductDto.setCategory("category");
        initialProductDto.setMark("mark");
        initialProductDto.setStatus("status");

        // Crée un ProductDto avec la quantité de stock après décrémentation
        ProductDto updatedProductDto = new ProductDto();
        updatedProductDto.setProductId(1L);
        updatedProductDto.setStockQuantity(8); // 10 - 2 = 8
        updatedProductDto.setProductName("productName");
        updatedProductDto.setDescription("description");
        updatedProductDto.setPrice(30f);
        updatedProductDto.setCategory("category");
        updatedProductDto.setMark("mark");
        updatedProductDto.setStatus("status");

        // Configure le mock pour retourner le produit mis à jour après décrémentation
        when(productService.decrementStock(1L, 2)).thenReturn(updatedProductDto);

        // Effectue la requête et vérifie les résultats
        mockMvc.perform(put("/api/products/1/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.stockQuantity").value(8))// Stock should be decremented by 2
                .andExpect(jsonPath("$.productName").value("productName"));
    }

}
