package com.aat.Library_Management_System.mappers;

import com.aat.Library_Management_System.dto.InventoryCopyDTO;
import com.aat.Library_Management_System.entities.InventoryCopy;
import com.aat.Library_Management_System.entities.Book;
import com.aat.Library_Management_System.entities.BorrowRecord;

public class InventoryCopyMapper {

    public static InventoryCopyDTO toDTO(InventoryCopy copy) {
        if (copy == null) return null;

        return InventoryCopyDTO.builder()
                .id(copy.getId())
                .barcode(copy.getBarcode())
                .status(copy.getStatus() != null ? copy.getStatus().name() : null)
                .bookId(copy.getBook() != null ? copy.getBook().getId() : null)
                .currentBorrowId(copy.getCurrentBorrow() != null ? copy.getCurrentBorrow().getId() : null)
                .build();
    }

    public static InventoryCopy toEntity(InventoryCopyDTO dto, Book book, BorrowRecord borrowRecord) {
        if (dto == null) return null;

        return InventoryCopy.builder()
                .id(dto.getId())
                .barcode(dto.getBarcode())
                .status(dto.getStatus() != null ? InventoryCopy.Status.valueOf(dto.getStatus()) : null)
                .book(book)
                .currentBorrow(borrowRecord)
                .build();
    }
}
