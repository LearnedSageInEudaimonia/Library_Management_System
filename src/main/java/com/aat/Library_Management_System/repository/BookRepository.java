package com.aat.Library_Management_System.repository;

import com.aat.Library_Management_System.dto.BookDTO;
import com.aat.Library_Management_System.entities.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);

    @EntityGraph(attributePaths = {"authors", "genres", "publisher"})
    List<Book> findAll();

    @Query("""
            select b from Book b
            join b.authors a
            where lower(b.title) like lower(concat('%', :q, '%'))
            or lower(a.name) like lower(concat('%', :q, '%'))
            or b.isbn = :q
            """)
    List<Book> search(String q);
    
    @Query("""
            select b from Book b
            join b.copies c
            join BorrowRecord br on br.copy = c
            group by b
            order by count(br) desc
            """)
    List<Book> findMostBorrowedBooks();


    // === Author count ===
    @Query("SELECT a.name, COUNT(b) FROM Book b JOIN b.authors a " +
            "GROUP BY a.id, a.name ORDER BY COUNT(b) DESC")
    List<Object[]> getAuthorCounts();

    // === Genre count ===
    @Query("SELECT g.name, COUNT(b) FROM Book b JOIN b.genres g " +
            "GROUP BY g.id, g.name ORDER BY COUNT(b) DESC")
    List<Object[]> getGenreCounts();
}