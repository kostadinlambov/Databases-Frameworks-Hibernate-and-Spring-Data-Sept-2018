package com.kl.cardealer.repositories;

import com.kl.cardealer.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query(value="SELECT s FROM Supplier AS s WHERE s.importer = false")
    List<Supplier> getAllByIsImporterFalse();
}
