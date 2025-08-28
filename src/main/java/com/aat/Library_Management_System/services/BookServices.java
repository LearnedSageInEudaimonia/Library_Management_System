package com.aat.Library_Management_System.services;

import com.aat.Library_Management_System.dto.BookDTO;
import com.aat.Library_Management_System.entities.*;
import com.aat.Library_Management_System.exceptions.NotFoundException;
import com.aat.Library_Management_System.mappers.AuthorMapper;
import com.aat.Library_Management_System.mappers.BookMapper;
import com.aat.Library_Management_System.repository.BookRepository;
import com.aat.Library_Management_System.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServices implements BookService {
    private final BookRepository bookRepository;



    @Override
    public BookDTO createBook(BookDTO bookDto) {
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .isbn(bookDto.getIsbn())
                .build();
        return BookMapper.toDTO(bookRepository.save(book));
    }

    @Override
    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id).map(BookMapper::toDTO).orElseThrow(()-> new NotFoundException(STR."Book with this id : \{id} not found"));
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(BookMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new NotFoundException(STR."Book with this id : \{id} not found"));
        book.setTitle(bookDto.getTitle());
        book.setIsbn(bookDto.getIsbn());
        return BookMapper.toDTO(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.findById(id).orElseThrow(()-> new NotFoundException(STR."Book with this id : \{id} not found"));
        bookRepository.deleteById(id);
    }

    public List<BookDTO> search(String query) {
        List<Book> books = bookRepository.search(query);

        return books.stream().map(BookMapper::toDTO).collect(Collectors.toList());
    }
    public List<BookDTO> getMostBorrowedBooks(int limit) {
        List<Book> books = bookRepository.findMostBorrowedBooks();

        return books.stream()
                .limit(limit)
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }
}
