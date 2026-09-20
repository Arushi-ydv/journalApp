package com.arushi.journalapp.repository;

import com.arushi.journalapp.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {

    List<JournalEntry> findByUser_UserName(String userName);

    Optional<JournalEntry> findByIdAndUser_UserName(Long id, String userName);
}