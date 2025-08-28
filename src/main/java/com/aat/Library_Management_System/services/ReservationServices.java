package com.aat.Library_Management_System.services.impl;

import com.aat.Library_Management_System.dto.ReservationDTO;
import com.aat.Library_Management_System.entities.Book;
import com.aat.Library_Management_System.entities.Member;
import com.aat.Library_Management_System.entities.Reservation;
import com.aat.Library_Management_System.repository.BookRepository;
import com.aat.Library_Management_System.repository.MemberRepository;
import com.aat.Library_Management_System.repository.ReservationRepository;
import com.aat.Library_Management_System.services.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServices implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    private ReservationDTO toDTO(Reservation reservation) {
        return ReservationDTO.builder()
                .id(reservation.getId())
                .reservationDate(reservation.getReservationDate())
                .status(String.valueOf(reservation.getStatus()))
                .bookId(reservation.getBook().getId())
                .memberId(reservation.getMember().getId())
                .build();
    }

    @Override
    public ReservationDTO enqueue(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Reservation reservation = Reservation.builder()
                .reservationDate(LocalDate.now())
                .status(Reservation.Status.QUEUED)
                .member(member)
                .book(book)
                .build();

        return toDTO(reservationRepository.save(reservation));
    }

    @Override
    public Optional<ReservationDTO> peekNext(Long bookId) {
        return reservationRepository.findFirstByBookIdAndStatusOrderByReservationDateAsc(bookId, Reservation.Status.QUEUED)
                .map(this::toDTO);
    }

    @Override
    public void markNotified(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setStatus(Reservation.Status.NOTIFIED);
        reservationRepository.save(reservation);
    }

    @Override
    public void cancel(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setStatus(Reservation.Status.CANCELLED);
        reservationRepository.save(reservation);
    }

    @Override
    public ReservationDTO getById(Long id) {
        return reservationRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @Override
    public List<ReservationDTO> getAll() {
        return reservationRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
