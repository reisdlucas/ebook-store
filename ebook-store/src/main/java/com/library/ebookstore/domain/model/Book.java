package com.library.ebookstore.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_BOOK")
@Data
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @SequenceGenerator(name = "SQ_BOOK", sequenceName = "SQ_BOOK", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SQ_BOOK")
    @Column(name = "ID_BOOK")
    private Long id;

    @Column(name = "NM_BOOK", nullable = false)
    private String name;

    @Column(name = "WT_BOOK", nullable = false)
    private String writer;

    @Column(name = "CP_BOOK", nullable = false)
    private String company;
}
