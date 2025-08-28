package com.aat.Library_Management_System.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDTO {
    private Long id;
    private String name;
    private Set<Long> bookIds;
}
