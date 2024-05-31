package com.inventory.repository;

import com.inventory.model.ProductsSold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsSoldRepo extends JpaRepository<ProductsSold,Long> {
}
