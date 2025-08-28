package com.aat.Library_Management_System.repository;

import com.aat.Library_Management_System.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);


    
    List<Member> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT COUNT(m) FROM Member m WHERE m.membershipDate >= :startDate")
    long countNewMembersThisMonth(@Param("startDate") LocalDate startDate);
    
    @Query("SELECT COUNT(DISTINCT m) FROM Member m JOIN m.borrowRecords br WHERE br.borrowDate >= :startDate")
    long countActiveMembers(@Param("startDate") LocalDate startDate);
    
    @Query("SELECT AVG(COUNT(br)) FROM Member m JOIN m.borrowRecords br GROUP BY m")
    double getAverageBooksPerMember();
}