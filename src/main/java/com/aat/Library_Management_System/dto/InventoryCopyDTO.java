package com.aat.Library_Management_System.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryCopyDTO {
    private Long id;
    private String barcode;
    private String status;
    private Long bookId;
    private Long currentBorrowId;
}
