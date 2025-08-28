package com.aat.Library_Management_System.mappers;

import com.aat.Library_Management_System.dto.BookDTO;
import com.aat.Library_Management_System.entities.*;

import java.util.Set;
import java.util.stream.Collectors;

public class BookMapper {

    // Entity -> DTO
    public static BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }

        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .publisherId(
                        book.getPublisher() != null ? book.getPublisher().getId() : null
                )
                .authorIds(
                        book.getAuthors() != null ?
                                book.getAuthors().stream()
                                        .map(Author::getId)
                                        .collect(Collectors.toSet())
                                : null
                )
                .genreIds(
                        book.getGenres() != null ?
                                book.getGenres().stream()
                                        .map(Genre::getId)
                                        .collect(Collectors.toSet())
                                : null
                )
                .build();
    }

    public static Book toEntity(BookDTO dto, Publisher publisher, Set<Author> authors, Set<Genre> genres) {
        if (dto == null) {
            return null;
        }

        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .isbn(dto.getIsbn())
                .publisher(publisher)
                .authors(authors != null ? authors : Set.of())
                .genres(genres != null ? genres : Set.of())
                .build();
    }
}
