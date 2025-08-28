package com.aat.Library_Management_System.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDTO {

    private long totalBooks;
    private long totalMembers;
    private long totalBorrows;
    private long totalCopies;


    private Map<String, Long> topGenres;          // genre name -> count
    private Map<String, Long> topAuthors;         // author name -> count
    private Map<String, Long> mostBorrowedBooks;  // book title -> count

    // === Member stats ===
    private Map<String, Long> membershipTypes; // e.g., Regular=20, Premium=5
    private double averageBorrowsPerMember;

    // === Inventory stats ===
    private Map<String, Long> inventoryStatus; // AVAILABLE=10, BORROWED=5, LOST=2
}
