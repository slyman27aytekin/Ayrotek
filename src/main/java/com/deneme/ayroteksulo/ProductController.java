package com.deneme.ayroteksulo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Tüm ürünleri getir
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Belirli bir kullanıcının ürünlerini getir
    @GetMapping("/owner/{ownerId}")
    public List<Product> getProductsByOwner(@PathVariable Long ownerId) {
        return productService.getProductsByOwner(ownerId);
    }

    // Yeni ürün ekle
    @PostMapping("/add")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // Ürün güncelle
    @PutMapping("/{productId}/owner/{ownerId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long productId,
            @RequestBody Product updatedProduct,
            @PathVariable Long ownerId) {
        Product product = productService.updateProduct(productId, updatedProduct, ownerId);
        return ResponseEntity.ok(product);
    }

    // Ürün sil
    @DeleteMapping("/{productId}/owner/{ownerId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long productId,
            @PathVariable Long ownerId) {
        productService.deleteProduct(productId, ownerId);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
