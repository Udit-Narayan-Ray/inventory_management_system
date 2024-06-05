package com.inventory.repository;

import com.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepo extends JpaRepository<Inventory,Long> {

    @Query("select inv from Inventory inv inner join inv.createdBy s where s.sellerId = :sellerId")
    public List<Inventory> findAllByCreatedBy(@Param("sellerId")Long sellerId);
}
