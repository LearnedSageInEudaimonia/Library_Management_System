package com.aat.Library_Management_System.controllers;

import com.aat.Library_Management_System.dto.ReservationDTO;
import com.aat.Library_Management_System.services.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/enqueue/{memberId}/{bookId}")
    public ResponseEntity<ReservationDTO> enqueue(
            @PathVariable Long memberId,
            @PathVariable Long bookId) {
        return ResponseEntity.ok(reservationService.enqueue(memberId, bookId));
    }

    @GetMapping("/peek/{bookId}")
    public ResponseEntity<ReservationDTO> peekNext(@PathVariable Long bookId) {
        return reservationService.peekNext(bookId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PutMapping("/{id}/notified")
    public ResponseEntity<Void> markNotified(@PathVariable Long id) {
        reservationService.markNotified(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        reservationService.cancel(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAll() {
        return ResponseEntity.ok(reservationService.getAll());
    }
}
