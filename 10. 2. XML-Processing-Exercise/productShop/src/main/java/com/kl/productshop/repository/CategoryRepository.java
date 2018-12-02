package com.kl.productshop.repository;

import com.kl.productshop.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT a.name, COUNT(b.id), AVG(b.price), SUM(b.price) " +
            "FROM Category AS a " +
            "JOIN a.products AS b " +
            "GROUP BY a.id " +
            "ORDER BY COUNT(b.id)DESC")
    List<Object[]> categoriesByProductCount();
}
