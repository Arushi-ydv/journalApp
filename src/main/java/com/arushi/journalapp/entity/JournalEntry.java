package com.arushi.journalapp.entity;

import jakarta.persistence.*;
import lombok.*;
import com.arushi.journalapp.enums.Sentiment;

import java.time.LocalDateTime;

@Entity
@Table(name = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private Sentiment sentiment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;
}
