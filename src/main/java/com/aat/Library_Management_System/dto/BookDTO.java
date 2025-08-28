package com.aat.Library_Management_System.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private Long publisherId;
    private Set<Long> authorIds;
    private Set<Long> genreIds;
}
