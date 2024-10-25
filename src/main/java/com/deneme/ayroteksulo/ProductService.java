package com.deneme.ayroteksulo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Tüm ürünleri getir
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Belirli bir kullanıcının ürünlerini getir
    public List<Product> getProductsByOwner(Long ownerId) {
        return productRepository.findByOwnerId(ownerId);
    }

    // Yeni ürün ekle
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Ürün güncelle
    public Product updateProduct(Long productId, Product updatedProduct, Long ownerId) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Sadece ürün sahibi güncelleyebilir
        if (!existingProduct.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("Unauthorized action");
        }

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(existingProduct);
    }

    // Ürün sil
    public void deleteProduct(Long productId, Long ownerId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Sadece ürün sahibi silebilir
        if (!product.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("Unauthorized action");
        }

        productRepository.delete(product);
    }
}
