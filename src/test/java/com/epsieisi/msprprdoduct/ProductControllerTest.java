package com.epsieisi.msprprdoduct;

import com.epsieisi.msprprdoduct.dto.ProductDto;
import com.epsieisi.msprprdoduct.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.BodyBuilder.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void createUserTest() throws Exception {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);
        ProductDto productDto = new ProductDto();
        productDto.setProductName("ballon basket G7");
        productDto.setDescription("ballon tailles 7");
        productDto.setCategory("Loisirs");
        productDto.setMark("Molten");
        productDto.setPrice(36.50f);
        productDto.setStockQuantity(25);
        productDto.setCreationDate(timestamp);
        productDto.setLastUpdate(timestamp);

        String productJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products").contentType(MediaType.APPLICATION_JSON).content(productJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productName").value("ballon basket G7"))
                .andExpect(jsonPath("$.description").value("ballon tailles 7"));
    }

    @Test
    public void getAllProductsTest() throws Exception{
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName", is("Samsung S23")));
    }


    @Test
    public void getProductByIdTest() throws Exception {
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", is("Samsung S23")));
    }

    @Test
    public void deleteProductByIdTest() throws Exception {
        Long idProduct = 1L;
        mockMvc.perform(delete("/api/products/{id}",idProduct).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /*
    @Test
    void testDecrementStock() {
        Long productId = 1L;
        Integer askedQuantity = 5;
        mockMvc.perform(put())
    }*/

}
