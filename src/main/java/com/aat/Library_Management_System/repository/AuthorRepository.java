package com.aat.Library_Management_System.repository;

import com.aat.Library_Management_System.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByNameContainingIgnoreCase(String name);

    
    @Query("""
            select a from Author a
            join a.books b
            join b.copies c
            join BorrowRecord br on br.copy = c
            group by a
            order by count(br) desc
            """)
    List<Author> findTopAuthorsByBorrowCount();
}