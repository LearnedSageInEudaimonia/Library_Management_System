package com.aat.Library_Management_System.mappers;

import com.aat.Library_Management_System.dto.AuthorDTO;
import com.aat.Library_Management_System.entities.Author;
import com.aat.Library_Management_System.entities.Book;

import java.util.Set;
import java.util.stream.Collectors;

public class AuthorMapper {


    public static AuthorDTO toDTO(Author author) {
        if (author == null) {
            return null;
        }

        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .biography(author.getBiography())
                .birthYear(author.getBirthYear())
                .bookIds(
                        author.getBooks() != null ?
                                author.getBooks().stream()
                                        .map(Book::getId)
                                        .collect(Collectors.toSet())
                                : null
                )
                .build();
    }
    public static Author toEntity(AuthorDTO dto, Set<Book> books) {
        if (dto == null) {
            return null;
        }

        return Author.builder()
                .id(dto.getId())
                .name(dto.getName())
                .biography(dto.getBiography())
                .birthYear(dto.getBirthYear())
                .books(books != null ? books : new java.util.HashSet<>())
                .build();
    }


}
