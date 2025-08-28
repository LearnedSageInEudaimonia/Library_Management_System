package com.aat.Library_Management_System.repository;

import com.aat.Library_Management_System.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByBookIdAndStatusOrderByReservationDateAsc(Long bookId, Reservation.Status status);
    
    boolean existsByMemberIdAndBookIdAndStatus(Long memberId, Long bookId, Reservation.Status status);
    
    boolean existsByMemberIdAndBookIdAndStatusIn(Long memberId, Long bookId, Collection<Reservation.Status> statuses);
    
    long countByStatus(Reservation.Status status);
    
    Optional<Reservation> findFirstByBookIdAndStatusOrderByReservationDateAsc(Long bookId, Reservation.Status status);
}