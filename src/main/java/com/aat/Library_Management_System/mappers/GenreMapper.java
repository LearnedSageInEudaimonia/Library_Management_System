package com.aat.Library_Management_System.mappers;

import com.aat.Library_Management_System.dto.GenreDTO;
import com.aat.Library_Management_System.entities.Book;
import com.aat.Library_Management_System.entities.Genre;

import java.util.Set;
import java.util.stream.Collectors;

public class GenreMapper {

    public static GenreDTO toDTO(Genre genre) {
        if (genre == null) return null;

        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .bookIds(
                        genre.getBooks() != null
                                ? genre.getBooks().stream().map(Book::getId).collect(Collectors.toSet())
                                : null
                )
                .build();
    }

    public static Genre toEntity(GenreDTO dto, Set<Book> books) {
        if (dto == null) return null;

        return Genre.builder()
                .id(dto.getId())
                .name(dto.getName())
                .books(books != null ? books : Set.of())
                .build();
    }
}
