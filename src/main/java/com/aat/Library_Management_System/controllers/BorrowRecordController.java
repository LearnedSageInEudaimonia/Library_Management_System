package com.aat.Library_Management_System.controllers;

import com.aat.Library_Management_System.dto.BorrowRecordDTO;
import com.aat.Library_Management_System.services.interfaces.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowRecordController {

    private final BorrowService borrowService;

//    @PostMapping("/{memberId}/{bookId}")
//    public ResponseEntity<BorrowRecordDTO> borrowBook(
//            @PathVariable Long memberId,
//            @PathVariable Long bookId) {
//        return ResponseEntity.ok(borrowService.borrow(memberId, bookId));
//    }
//
//    @PostMapping("/return/{barcode}")
//    public ResponseEntity<BorrowRecordDTO> returnBook(
//            @PathVariable String barcode) {
//        return ResponseEntity.ok(borrowService.returnByBarcode(barcode));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowRecordDTO> getBorrowRecord(@PathVariable Long id) {
        return ResponseEntity.ok(borrowService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BorrowRecordDTO>> getAllRecords() {
        return ResponseEntity.ok(borrowService.getAll());
    }

    @GetMapping("/most-borrowed-book")
    public ResponseEntity<String> mostBorrowedBook() {
        return borrowService.findMostBorrowedBook()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/top-member")
    public ResponseEntity<String> topMember() {
        return borrowService.findTopMember()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
