package com.inventory.repository;

import com.inventory.model.ProductsSoldDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsSoldDetailsRepo extends JpaRepository<ProductsSoldDetails,Long> {
}
