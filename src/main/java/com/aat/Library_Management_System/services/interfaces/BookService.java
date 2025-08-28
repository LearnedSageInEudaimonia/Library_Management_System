package com.aat.Library_Management_System.services.interfaces;

import com.aat.Library_Management_System.dto.BookDTO;
import java.util.List;

public interface BookService {
    BookDTO createBook(BookDTO bookDto);
    BookDTO getBookById(Long id);
    List<BookDTO> getAllBooks();
    BookDTO updateBook(Long id, BookDTO bookDto);
    void deleteBook(Long id);
}
