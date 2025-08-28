package com.aat.Library_Management_System.controllers;

import com.aat.Library_Management_System.dto.BookDTO;
import com.aat.Library_Management_System.services.BookServices;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookServices bookService;

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.createBook(bookDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/search")
    public ResponseEntity<List<BookDTO>> searchByName(@RequestParam String name){
        return ResponseEntity.ok(bookService.search(name));
    }

    @GetMapping("/borrowed/{limit}")
    public ResponseEntity<List<BookDTO>> getBorrowedBooks(@PathVariable int limit){
        return ResponseEntity.ok(bookService.getMostBorrowedBooks(limit));
    }
}
