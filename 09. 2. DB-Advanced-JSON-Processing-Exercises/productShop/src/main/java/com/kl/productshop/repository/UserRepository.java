package com.kl.productshop.repository;

import com.kl.productshop.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM Product AS p " +
            "JOIN p.seller AS u "+
            "WHERE p.buyer IS NOT NULL " +
            "ORDER BY u.lastName, u.firstName"
    )
    List<User> getAllSellers();
}
