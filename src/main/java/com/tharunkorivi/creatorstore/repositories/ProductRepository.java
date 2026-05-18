package com.tharunkorivi.creatorstore.repositories;

import com.tharunkorivi.creatorstore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
