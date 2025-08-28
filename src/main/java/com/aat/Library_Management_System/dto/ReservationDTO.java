package com.aat.Library_Management_System.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {
    private Long id;
    private LocalDate reservationDate;
    private String status;
    private Long bookId;
    private Long memberId;
}
