package com.aat.Library_Management_System.services.interfaces;

import com.aat.Library_Management_System.dto.BorrowRecordDTO;
import com.aat.Library_Management_System.entities.BorrowRecord;
import com.aat.Library_Management_System.entities.InventoryCopy;

import java.util.List;
import java.util.Optional;

public interface BorrowService {
    BorrowRecordDTO borrow(Long memberId, Long bookId);
    BorrowRecordDTO returnByBarcode(String barcode);
    BorrowRecordDTO getById(Long id);
    List<BorrowRecordDTO> getAll();
    Optional<String> findMostBorrowedBook();
    Optional<String> findTopMember();
    Optional<BorrowRecord> findByCopyAndReturnDateIsNull(InventoryCopy copy);
    List<BorrowRecord> findByMemberId(Long memberId);
}
