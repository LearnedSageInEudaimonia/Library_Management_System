package com.aat.Library_Management_System.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AuthorDTO {
    private Long id;
    private String name;
    private String biography;
    private Integer birthYear;
    private Set<Long> bookIds;
}
