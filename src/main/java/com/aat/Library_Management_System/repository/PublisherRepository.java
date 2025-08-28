package com.aat.Library_Management_System.repository;

import com.aat.Library_Management_System.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findByNameIgnoreCase(String name);

    Optional<Publisher> findByName(String publisherName);
}