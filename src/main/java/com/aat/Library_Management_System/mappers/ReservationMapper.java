package com.aat.Library_Management_System.mappers;

import com.aat.Library_Management_System.dto.ReservationDTO;
import com.aat.Library_Management_System.entities.Reservation;

public class ReservationMapper {

    public static ReservationDTO toDTO(Reservation reservation) {
        if (reservation == null) return null;

        return ReservationDTO.builder()
                .id(reservation.getId())
                .reservationDate(reservation.getReservationDate())
                .status(reservation.getStatus().name())
                .bookId(reservation.getBook() != null ? reservation.getBook().getId() : null)
                .memberId(reservation.getMember() != null ? reservation.getMember().getId() : null)
                .build();
    }

    public static Reservation toEntity(ReservationDTO dto) {
        if (dto == null) return null;

        Reservation reservation = new Reservation();
        reservation.setId(dto.getId());
        reservation.setReservationDate(dto.getReservationDate());

        if (dto.getStatus() != null) {
            reservation.setStatus(Reservation.Status.valueOf(dto.getStatus()));
        }

        return reservation;
    }
}
