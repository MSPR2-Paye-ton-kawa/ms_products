package com.epsieisi.msprprdoduct.repositories;

import com.epsieisi.msprprdoduct.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
