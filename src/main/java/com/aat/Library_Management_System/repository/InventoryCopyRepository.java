package com.aat.Library_Management_System.repository;

import com.aat.Library_Management_System.entities.InventoryCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryCopyRepository extends JpaRepository<InventoryCopy, Long> {
    Optional<InventoryCopy> findFirstByBookIdAndStatus(Long bookId, InventoryCopy.Status status);
    Optional<InventoryCopy> findByBarcode(String barcode);

    long countByBookIdAndStatus(Long bookId, InventoryCopy.Status status);
}