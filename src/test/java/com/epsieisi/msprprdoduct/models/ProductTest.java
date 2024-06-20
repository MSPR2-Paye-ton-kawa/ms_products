package com.epsieisi.msprprdoduct.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductTest {
    @Test
    void testProductAllArgsConstructor() {
        Product product = new Product("productname", "description ", 10f, 10, "category", "mark","status");

        assertThat(product).isNotNull();
        assertThat(product.getProductName()).isEqualTo("productname");
        assertThat(product.getPrice()).isEqualTo(10f);
        assertThat(product.getStockQuantity()).isEqualTo(10);
        assertThat(product.getMark()).isEqualTo("mark");
        assertThat(product.getStatus()).isEqualTo("status");
    }

    @Test
    void testProductSettersAndGetters(){
        Product product = new Product();

        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);

        product.setProductName("productName");
        product.setDescription("description");
        product.setPrice(30f);
        product.setStockQuantity(10);
        product.setCategory("category");
        product.setMark("mark");
        product.setStatus("status");
        product.setCreationDate(timestamp);
        product.setLastUpdate(timestamp);

        assertThat(product.getProductName()).isEqualTo("productName");
        assertThat(product.getDescription()).isEqualTo("description");
        assertThat(product.getPrice()).isEqualTo(30f);
        assertThat(product.getStockQuantity()).isEqualTo(10);
        assertThat(product.getCategory()).isEqualTo("category");
        assertThat(product.getMark()).isEqualTo("mark");
    }
}
