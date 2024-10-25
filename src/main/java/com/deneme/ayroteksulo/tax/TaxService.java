package com.deneme.ayroteksulo.tax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    @Autowired
    private TaxLogRepository taxLogRepository;

    // Vergi hesapla (%10 örneği)
    public double calculateTax(double price) {
        return price * 0.10;
    }

    // Vergi logunu MongoDB'ye kaydet
    public void logTaxCalculation(String productId, String ownerId, double taxAmount) {
        TaxLog taxLog = new TaxLog(productId, ownerId, taxAmount);
        taxLogRepository.save(taxLog);  // MongoDB'ye kaydet
    }
}
