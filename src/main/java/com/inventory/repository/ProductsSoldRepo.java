package com.inventory.repository;

import com.inventory.model.ProductsSold;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProductsSoldRepo extends JpaRepository<ProductsSold,Long> {
    @Query("select order from ProductsSold order inner join order.createdBy seller where seller.sellerId = :sellerId")
    public List<ProductsSold> findAllByCreatedBy(@Param("sellerId")Long sellerId);

    @Query("select order from ProductsSold order inner join order.createdBy seller where seller.sellerId = :sellerId")
    public Page<ProductsSold> findAllByCreatedBy(@Param("sellerId")Long sellerId, Pageable pageable);

    @Query("select order from ProductsSold order inner join order.createdBy seller where seller.sellerId = :sellerId and Date(order.createAt) = :date")
    public List<ProductsSold> findAllByCreatedByAndCreatedAt(@Param("sellerId")Long sellerId,@Param("date") Date date);

    @Query("select order from ProductsSold order inner join order.createdBy seller where seller.sellerId =:sellerId and (order.customerName like CONCAT('%', :search,'%') or order.phoneNo like CONCAT('%', :search,'%'))")
    public Page<ProductsSold> findAllByCreatedByAndSearch(@Param("sellerId")Long sellerId,@Param("search")String search,Pageable pageable);

}
