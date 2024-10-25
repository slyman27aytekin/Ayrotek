package com.deneme.ayroteksulo.tax;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaxLogRepository extends MongoRepository<TaxLog, String> {
    List<TaxLog> findByOwnerId(String ownerId);
    List<TaxLog> findByProductId(String productId);
}
