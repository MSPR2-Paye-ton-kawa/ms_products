package com.epsieisi.msprprdoduct.repositories;

import com.epsieisi.msprprdoduct.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Product product1 = new Product("productName1", "description1", 10f, 10, "category1", "mark1","status1");
        Product product2 = new Product("productName2", "description2", 20f, 20, "category2", "mark2","status2");
        Product product3 = new Product("productName3", "description3", 30f, 30, "category3", "mark3","status3");
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);
        entityManager.flush();
    }

    @Test
    void findAll_Should_ReturnProductsList() {
        assertThat(productRepository.findAll()).hasSize(3);
    }

    @Test
    void findAll_Should_ReturnProductsListWithCorrectData() {
        assertThat(productRepository.findAll()).extracting(Product::getProductName, Product::getPrice).containsExactlyInAnyOrder(tuple("productName1", 10f), tuple("productName2", 20f), tuple("productName3", 30f));
    }

    @Test
    void findById_Should_ReturnProduct() {
        // save new Product
        Product playStation = new Product("PS5", "playstation 5 V2.0", 542.36f, 20, "Jeux multimedia", "Sony","promotion");
        Product savedProduct = productRepository.save(playStation);

        Optional<Product> product = productRepository.findById(savedProduct.getProductId());

        assertThat(product).isPresent()
                .hasValueSatisfying(c -> {
                    assertThat(c)
                            .hasFieldOrPropertyWithValue("productName", "PS5")
                            .hasFieldOrPropertyWithValue("stockQuantity", 20)
                            .hasFieldOrPropertyWithValue("mark", "Sony");
                });
    }

    @Test
    void save_Should_InsertNewProduct() {
        Product product1 = new Product("productName1", "description1", 10f, 10, "category1", "mark1","status1");
        Product product = productRepository.save(product1);

        assertThat(product).isNotNull()
                .hasFieldOrPropertyWithValue("productName", "productName1")
                .hasFieldOrPropertyWithValue("description", "description1")
                .hasFieldOrPropertyWithValue("price", 10f)
                .hasFieldOrPropertyWithValue("stockQuantity", 10);
    }

    @Test
    void update_Should_UpdateProduct() {
        Product product2 = new Product("productName2", "description2", 20f, 20, "category2", "mark2","status2");
        Product savedProduct = productRepository.save(product2);

        savedProduct.setProductName("productName3");
        savedProduct.setDescription("description3");
        savedProduct.setPrice(30f);
        savedProduct.setStockQuantity(30);
        savedProduct.setCategory("category3");
        savedProduct.setMark("mark3");
        savedProduct.setStatus("status3");

        Product updatedProduct = productRepository.save(savedProduct);

        assertThat(updatedProduct)
                .isNotNull()
                .hasFieldOrPropertyWithValue("productName", "productName3")
                .hasFieldOrPropertyWithValue("description", "description3")
                .hasFieldOrPropertyWithValue("stockQuantity", 30)
                .hasFieldOrPropertyWithValue("category", "category3")
                .hasFieldOrPropertyWithValue("price", 30f)
                .hasFieldOrPropertyWithValue("mark", "mark3")
                .hasFieldOrPropertyWithValue("status", "status3");
    }

    @Test
    void delete_Should_DeleteProduct() {
        Product product4 = new Product("productName4", "description4", 40f, 40, "category4", "mark4","status4");
        Product savedProduct = productRepository.save(product4);

        productRepository.delete(savedProduct);

        assertThat(productRepository.findById(savedProduct.getProductId())).isNotPresent();
    }
}
