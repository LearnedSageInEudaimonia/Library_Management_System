package com.aat.Library_Management_System.controllers;

import com.aat.Library_Management_System.dto.AuthorDTO;

import com.aat.Library_Management_System.entities.*;
import com.aat.Library_Management_System.services.AuthorServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorServices authorService;


    @GetMapping("/all")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
       return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getById(id));
    }

    @PostMapping({"/new"})
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorService.create(authorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id,
            @RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorService.update(id,authorDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
       authorService.delete(id);
       return ResponseEntity.noContent().build();

    }

    @GetMapping("/find")
    public ResponseEntity<List<AuthorDTO>> findByName(@RequestParam String name){
        return ResponseEntity.ok(authorService.searchByName(name));
    }

    @GetMapping("/top-author/{limit}")
    public ResponseEntity<List<AuthorDTO>> findTopAuthorsBasedOnBorrowCount(@PathVariable int limit){
        return ResponseEntity.ok(authorService.findTopAuthorsBasedOnBorrowCount(limit));
    }
}
