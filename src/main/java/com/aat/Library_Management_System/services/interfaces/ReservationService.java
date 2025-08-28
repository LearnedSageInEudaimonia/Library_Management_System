package com.aat.Library_Management_System.services.interfaces;

import com.aat.Library_Management_System.dto.ReservationDTO;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    ReservationDTO enqueue(Long memberId, Long bookId);
    Optional<ReservationDTO> peekNext(Long bookId);
    void markNotified(Long reservationId);
    void cancel(Long reservationId);
    ReservationDTO getById(Long id);
    List<ReservationDTO> getAll();
}
