package com.tharunkorivi.creatorstore.services;


import com.tharunkorivi.creatorstore.entities.Product;
import com.tharunkorivi.creatorstore.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {


    ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    public Product updateProduct(Long id,Product product) {
        Product existingProduct = productRepository.findById((id))
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStockQuantity(product.getStockQuantity());
        existingProduct.setCategory(product.getCategory());

        return productRepository.save(existingProduct);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }


    public void deleteProductById(Long id) {
        productRepository.deleteById((id));
    }
}
