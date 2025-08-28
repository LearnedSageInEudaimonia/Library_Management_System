package com.aat.Library_Management_System.services;

import com.aat.Library_Management_System.dto.ReportDTO;
import com.aat.Library_Management_System.entities.Book;

import com.aat.Library_Management_System.entities.Member;
import com.aat.Library_Management_System.repository.BookRepository;
import com.aat.Library_Management_System.repository.BorrowRecordRepository;
import com.aat.Library_Management_System.repository.InventoryCopyRepository;
import com.aat.Library_Management_System.repository.MemberRepository;
import com.aat.Library_Management_System.services.interfaces.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServices implements ReportService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BorrowRecordRepository borrowRecordRepository;
    private final InventoryCopyRepository inventoryCopyRepository;

    @Override
    public ReportDTO getDashboardReport() {
        long totalBooks = bookRepository.count();
        long totalMembers = memberRepository.count();
        long totalBorrows = borrowRecordRepository.count();
        long totalCopies = inventoryCopyRepository.count();


        Map<String, Long> topGenres = bookRepository.getGenreCounts()
                .stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));


        Map<String, Long> topAuthors = bookRepository.getAuthorCounts()
                .stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));

        Map<String, Long> mostBorrowedBooks = borrowRecordRepository.findAll().stream()
                .collect(Collectors.groupingBy(br -> br.getCopy().getBook().getTitle(), Collectors.counting()));


        Map<String, Long> membershipTypes = memberRepository.findAll().stream()
                .collect(Collectors.groupingBy(Member::getMembershipType, Collectors.counting()));

        double avgBorrowsPerMember = totalMembers == 0 ? 0.0 :
                (double) totalBorrows / totalMembers;


        Map<String, Long> inventoryStatus = inventoryCopyRepository.findAll().stream()
                .collect(Collectors.groupingBy(copy -> copy.getStatus().name(), Collectors.counting()));

        return ReportDTO.builder()
                .totalBooks(totalBooks)
                .totalMembers(totalMembers)
                .totalBorrows(totalBorrows)
                .totalCopies(totalCopies)
                .topGenres(topGenres)
                .topAuthors(topAuthors)
                .mostBorrowedBooks(mostBorrowedBooks)
                .membershipTypes(membershipTypes)
                .averageBorrowsPerMember(avgBorrowsPerMember)
                .inventoryStatus(inventoryStatus)
                .build();
    }


}
