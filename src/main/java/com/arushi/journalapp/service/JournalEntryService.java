package com.arushi.journalapp.service;

import com.arushi.journalapp.entity.JournalEntry;
import com.arushi.journalapp.entity.User;
import com.arushi.journalapp.repository.JournalEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final UserService userService;

    public JournalEntryService(JournalEntryRepository journalEntryRepository,
                               UserService userService) {
        this.journalEntryRepository = journalEntryRepository;
        this.userService = userService;
    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {

        User user = userService.findByUserName(userName);

        journalEntry.setDate(LocalDateTime.now());
        journalEntry.setUser(user);

        JournalEntry savedEntry = journalEntryRepository.save(journalEntry);

        user.getJournalEntries().add(savedEntry);
        userService.saveUser(user);
    }

    public List<JournalEntry> getAllEntriesByUser(String userName) {
        return journalEntryRepository.findByUser_UserName(userName);
    }

    @Transactional
    public boolean deleteById(Long id, String userName) {

        Optional<JournalEntry> entry =
                journalEntryRepository.findByIdAndUser_UserName(id, userName);

        if (entry.isEmpty()) {
            return false;
        }

        journalEntryRepository.delete(entry.get());
        return true;
    }

    public Optional<JournalEntry> findByIdForUser(Long id, String userName) {
        return journalEntryRepository.findByIdAndUser_UserName(id, userName);
    }

    public Optional<JournalEntry> updateEntry(
            Long id,
            String userName,
            String title,
            String content) {

        Optional<JournalEntry> optionalEntry =
                journalEntryRepository.findByIdAndUser_UserName(id, userName);

        if (optionalEntry.isEmpty()) {
            return Optional.empty();
        }

        JournalEntry entry = optionalEntry.get();

        if (title != null && !title.isBlank()) {
            entry.setTitle(title);
        }

        if (content != null && !content.isBlank()) {
            entry.setContent(content);
        }

        return Optional.of(journalEntryRepository.save(entry));
    }
}