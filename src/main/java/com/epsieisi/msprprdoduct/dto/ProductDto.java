package com.epsieisi.msprprdoduct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto{
    private Long productId;
    private String productName;
    private String description;
    private Float price;
    private int stockQuantity;
    private String category;
    private String mark;
    private Date creationDate;
    private Date lastUpdate;
    private String status;
}
