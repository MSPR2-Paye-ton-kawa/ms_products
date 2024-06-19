package com.epsieisi.msprprdoduct.models;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {
    @Test
    void testProductAllArgsConstructor() {
        Product playStation = new Product("PS5", "playstation 5 V2.0", 542.36f, 20, "Jeux multimedia", "Sony","promotion");

        assertThat(playStation).isNotNull();
        assertThat(playStation.getProductName()).isEqualTo("PS5");
        assertThat(playStation.getPrice()).isEqualTo(542.36f);
        assertThat(playStation.getStockQuantity()).isEqualTo(20);
        assertThat(playStation.getMark()).isEqualTo("Sony");
        assertThat(playStation.getStatus()).isEqualTo("promotion");
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
