package com.aat.Library_Management_System.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private Long id;
    private String name;
    private String email;
    private String membershipType;
    private LocalDate membershipDate;
}
