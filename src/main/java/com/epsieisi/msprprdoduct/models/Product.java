package com.epsieisi.msprprdoduct.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    private String description;

    private Float price;

    private int stockQuantity;

    private String category;

    private String mark;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_update")
    private Date lastUpdate;

    private String status;


    public Product(String productName, String description, Float price, int stockQuantity, String category, String mark, String status) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.mark = mark;
        this.status = status;
    }
}
