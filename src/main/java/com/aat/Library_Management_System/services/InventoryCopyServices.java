package com.aat.Library_Management_System.services;

import com.aat.Library_Management_System.dto.InventoryCopyDTO;
import com.aat.Library_Management_System.entities.Book;
import com.aat.Library_Management_System.entities.BorrowRecord;
import com.aat.Library_Management_System.entities.InventoryCopy;
import com.aat.Library_Management_System.mappers.InventoryCopyMapper;
import com.aat.Library_Management_System.repository.BookRepository;
import com.aat.Library_Management_System.repository.BorrowRecordRepository;
import com.aat.Library_Management_System.repository.InventoryCopyRepository;
import com.aat.Library_Management_System.services.interfaces.InventoryCopyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryCopyServices implements InventoryCopyService {

    private final InventoryCopyRepository inventoryCopyRepository;
    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    @Override
    public InventoryCopyDTO createInventoryCopy(InventoryCopyDTO dto) {
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        BorrowRecord borrowRecord = null;
        if (dto.getCurrentBorrowId() != null) {
            borrowRecord = borrowRecordRepository.findById(dto.getCurrentBorrowId())
                    .orElseThrow(() -> new EntityNotFoundException("Borrow record not found"));
        }

        InventoryCopy copy = InventoryCopy.builder()
                .barcode(dto.getBarcode())
                .status(InventoryCopy.Status.valueOf(dto.getStatus()))
                .book(book)
                .currentBorrow(borrowRecord)
                .build();

        return InventoryCopyMapper.toDTO(inventoryCopyRepository.save(copy));
    }

    @Override
    public InventoryCopyDTO getInventoryCopyById(Long id) {
        InventoryCopy copy = inventoryCopyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory copy not found"));
        return InventoryCopyMapper.toDTO(copy);
    }

    @Override
    public List<InventoryCopyDTO> getAllInventoryCopies() {
        return inventoryCopyRepository.findAll()
                .stream()
                .map(InventoryCopyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryCopyDTO updateInventoryCopy(Long id, InventoryCopyDTO dto) {
        InventoryCopy copy = inventoryCopyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory copy not found"));

        copy.setBarcode(dto.getBarcode());
        copy.setStatus(InventoryCopy.Status.valueOf(dto.getStatus()));

        if (dto.getBookId() != null) {
            Book book = bookRepository.findById(dto.getBookId())
                    .orElseThrow(() -> new EntityNotFoundException("Book not found"));
            copy.setBook(book);
        }

        if (dto.getCurrentBorrowId() != null) {
            BorrowRecord borrowRecord = borrowRecordRepository.findById(dto.getCurrentBorrowId())
                    .orElseThrow(() -> new EntityNotFoundException("Borrow record not found"));
            copy.setCurrentBorrow(borrowRecord);
        }

        return InventoryCopyMapper.toDTO(inventoryCopyRepository.save(copy));
    }

    @Override
    public void deleteInventoryCopy(Long id) {
        if (!inventoryCopyRepository.existsById(id)) {
            throw new EntityNotFoundException("Inventory copy not found");
        }
        inventoryCopyRepository.deleteById(id);
    }
}
