package com.aat.Library_Management_System.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"book", "currentBorrow"})
@Table(name = "inventory_copies", uniqueConstraints = @UniqueConstraint(columnNames = "barcode"))
public class InventoryCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String barcode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToOne(mappedBy = "copy",cascade = CascadeType.ALL, orphanRemoval = true)
    private BorrowRecord currentBorrow;

    public enum Status {
        AVAILABLE, 
        BORROWED, 
        RESERVED, 
        LOST
    }
}
