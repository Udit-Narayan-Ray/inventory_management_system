package com.inventory.repository;

import com.inventory.model.ProductsSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsSoldRepo extends JpaRepository<ProductsSold,Long> {

    @Query("select order from ProductsSold order inner join order.createdBy s where s.sellerId = :sellerId")
    public List<ProductsSold> findAllByCreatedBy(@Param("sellerId")Long sellerId);
}
