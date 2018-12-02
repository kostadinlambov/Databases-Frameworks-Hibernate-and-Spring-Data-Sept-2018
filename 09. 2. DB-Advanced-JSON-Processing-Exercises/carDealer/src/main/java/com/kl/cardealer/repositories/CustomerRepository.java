package com.kl.cardealer.repositories;

import com.kl.cardealer.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "SELECT c FROM Customer AS c ORDER BY c.birthDate ASC, c.youngDriver DESC")
    List<Customer> getAllCustomersOrdered();

    @Query(value = "SELECT c FROM Customer AS c WHERE SIZE(c.sales) > 0")
    List<Customer> findAllBySales();
}
