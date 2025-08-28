package com.aat.Library_Management_System.repository;

import com.aat.Library_Management_System.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByNameIgnoreCase(String name);

    Optional<Genre> findByName(String genreName);
    
    @Query("""
            select g from Genre g
            join g.books b
            join b.copies c
            join BorrowRecord br on br.copy = c
            group by g
            order by count(br) desc
            """)
    List<Genre> findTopGenresByBorrowCount(int limit);
}