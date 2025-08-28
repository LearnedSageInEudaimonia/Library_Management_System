package com.aat.Library_Management_System.services;

import com.aat.Library_Management_System.dto.BorrowRecordDTO;
import com.aat.Library_Management_System.entities.BorrowRecord;
import com.aat.Library_Management_System.entities.InventoryCopy;
import com.aat.Library_Management_System.entities.Member;
import com.aat.Library_Management_System.exceptions.BusinessRuleException;
import com.aat.Library_Management_System.exceptions.NotFoundException;
import com.aat.Library_Management_System.mappers.BorrowRecordMapper;
import com.aat.Library_Management_System.repository.BorrowRecordRepository;
import com.aat.Library_Management_System.repository.InventoryCopyRepository;
import com.aat.Library_Management_System.repository.MemberRepository;
import com.aat.Library_Management_System.services.interfaces.BorrowService;
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
public class BorrowServices implements BorrowService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final MemberRepository memberRepository;
    private final InventoryCopyRepository inventoryCopyRepository;

    @Override
    public BorrowRecordDTO borrow(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(STR."Member not found with id: \{memberId}"));

        InventoryCopy availableCopy = inventoryCopyRepository.findFirstByBookIdAndStatus(bookId, InventoryCopy.Status.AVAILABLE)
                .orElseThrow(() -> new BusinessRuleException("No available copies for this book"));

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setMember(member);
        borrowRecord.setCopy(availableCopy);
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecord.setDueDate(LocalDate.now().plusDays(14));
        availableCopy.setStatus(InventoryCopy.Status.BORROWED);
        inventoryCopyRepository.save(availableCopy);
        
        BorrowRecord savedRecord = borrowRecordRepository.save(borrowRecord);
        return BorrowRecordMapper.toDTO(savedRecord);
    }

    @Override
    public BorrowRecordDTO returnByBarcode(String barcode) {
        InventoryCopy copy = inventoryCopyRepository.findByBarcode(barcode)
                .orElseThrow(() -> new NotFoundException(STR."Copy not found with barcode: \{barcode}"));
        
        BorrowRecord borrowRecord = borrowRecordRepository.findByCopyAndReturnDateIsNull(copy)
                .orElseThrow(() -> new BusinessRuleException("No active borrow record found for this copy"));

        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecordRepository.save(borrowRecord);
        copy.setStatus(InventoryCopy.Status.AVAILABLE);
        inventoryCopyRepository.save(copy);
        
        return BorrowRecordMapper.toDTO(borrowRecord);
    }

    @Override
    public BorrowRecordDTO getById(Long id) {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Borrow record not found with id: " + id));
        return BorrowRecordMapper.toDTO(borrowRecord);
    }

    @Override
    public List<BorrowRecordDTO> getAll() {
        return borrowRecordRepository.findAll().stream()
                .map(BorrowRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<String> findMostBorrowedBook() {
        return borrowRecordRepository.findMostBorrowedBook();
    }

    @Override
    public Optional<String> findTopMember() {
        return borrowRecordRepository.findTopMember();
    }

    @Override
    public Optional<BorrowRecord> findByCopyAndReturnDateIsNull(InventoryCopy copy) {
        return borrowRecordRepository.findByCopyAndReturnDateIsNull(copy);
    }

    @Override
    public List<BorrowRecord> findByMemberId(Long memberId) {
        return borrowRecordRepository.findByMemberId(memberId);
    }

}
