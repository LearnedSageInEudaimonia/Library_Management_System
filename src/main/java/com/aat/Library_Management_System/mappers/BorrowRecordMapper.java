package com.aat.Library_Management_System.mappers;

import com.aat.Library_Management_System.dto.BorrowRecordDTO;
import com.aat.Library_Management_System.entities.BorrowRecord;
import com.aat.Library_Management_System.entities.InventoryCopy;
import com.aat.Library_Management_System.entities.Member;

public class BorrowRecordMapper {

    public static BorrowRecordDTO toDTO(BorrowRecord record) {
        if (record == null) return null;

        return BorrowRecordDTO.builder()
                .id(record.getId())
                .borrowDate(record.getBorrowDate())
                .dueDate(record.getDueDate())
                .returnDate(record.getReturnDate())
                .fine(record.getFine())
                .memberId(record.getMember() != null ? record.getMember().getId() : null)
                .copyId(record.getCopy() != null ? record.getCopy().getId() : null)
                .build();
    }

    public static BorrowRecord toEntity(BorrowRecordDTO dto, Member member, InventoryCopy copy) {
        if (dto == null) return null;

        return BorrowRecord.builder()
                .id(dto.getId())
                .borrowDate(dto.getBorrowDate())
                .dueDate(dto.getDueDate())
                .returnDate(dto.getReturnDate())
                .fine(dto.getFine())
                .member(member)
                .copy(copy)
                .build();
    }
}
