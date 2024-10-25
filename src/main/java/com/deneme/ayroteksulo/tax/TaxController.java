package com.deneme.ayroteksulo.tax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deneme.ayroteksulo.Product;
import com.deneme.ayroteksulo.ProductRepository;

@RestController
@RequestMapping("/api/tax")
public class TaxController {

    @Autowired
    private TaxService taxService;

    @Autowired
    private ProductRepository productRepository;

    // Kullanıcının belirli bir ürünü için vergi hesapla
    @GetMapping("/calculate/{productId}/owner/{ownerId}")
    public ResponseEntity<?> calculateTaxForProduct(
            @PathVariable Long productId,
            @PathVariable Long ownerId) {

        // Ürünü bul
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Ürün yok!"));

        // Ürün sahibini doğrula
        if (!product.getOwner().getId().equals(ownerId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Bu ürün için vergi hesaplama yetkiniz yok!");
        }

        // Vergiyi hesapla
        double taxAmount = taxService.calculateTax(product.getPrice());
        taxService.logTaxCalculation(productId.toString(), ownerId.toString(), taxAmount);

        

        return ResponseEntity.ok("Ürünün vergisi --> " + product.getName() + ": " + taxAmount);
    }
}

