package com.aat.Library_Management_System.repository;

import com.aat.Library_Management_System.entities.BorrowRecord;
import com.aat.Library_Management_System.entities.InventoryCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    long countByMemberIdAndReturnDateIsNull(Long memberId);
    List<BorrowRecord> findByMemberIdOrderByBorrowDateDesc(Long memberId);
    
    List<BorrowRecord> findByMemberId(Long memberId);
    
    Optional<BorrowRecord> findByCopyAndReturnDateIsNull(InventoryCopy copy);
    
    @Query("SELECT b.title FROM Book b JOIN InventoryCopy ic ON ic.book = b JOIN BorrowRecord br ON br.copy = ic GROUP BY b.title ORDER BY COUNT(br) DESC")
    Optional<String> findMostBorrowedBook();
    
    @Query("SELECT m.name FROM Member m JOIN BorrowRecord br ON br.member = m GROUP BY m.name ORDER BY COUNT(br) DESC")
    Optional<String> findTopMember();
}