package com.arushi.journalapp.controller;

import com.arushi.journalapp.dto.JournalEntryRequest;
import com.arushi.journalapp.dto.JournalEntryResponse;
import com.arushi.journalapp.dto.JournalEntryUpdateRequest;
import com.arushi.journalapp.entity.JournalEntry;
import com.arushi.journalapp.service.JournalEntryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private final JournalEntryService journalEntryService;

    public JournalEntryController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    private String getLoggedInUserName() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

    @GetMapping
    public ResponseEntity<List<JournalEntryResponse>> getAllJournalEntriesOfUser() {

        String userName = getLoggedInUserName();

        List<JournalEntryResponse> responses =
                journalEntryService.getAllEntriesByUser(userName)
                        .stream()
                        .map(this::convertToResponse)
                        .collect(Collectors.toList());

        if (!responses.isEmpty()) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntryResponse> createEntry(
            @Valid @RequestBody JournalEntryRequest request) {

        String userName = getLoggedInUserName();

        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setTitle(request.getTitle());
        journalEntry.setContent(request.getContent());

        journalEntryService.saveEntry(journalEntry, userName);

        return new ResponseEntity<>(
                convertToResponse(journalEntry),
                HttpStatus.CREATED
        );
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntryResponse> getJournalEntryById(
            @PathVariable Long myId) {

        String userName = getLoggedInUserName();

        return journalEntryService.findByIdForUser(myId, userName)
                .map(entry -> new ResponseEntity<>(
                        convertToResponse(entry),
                        HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(
            @PathVariable Long myId) {

        String userName = getLoggedInUserName();

        boolean removed =
                journalEntryService.deleteById(myId, userName);

        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntryResponse> updateJournalById(
            @PathVariable Long myId,
            @Valid @RequestBody JournalEntryUpdateRequest request) {

        String userName = getLoggedInUserName();

        return journalEntryService.updateEntry(
                        myId,
                        userName,
                        request.getTitle(),
                        request.getContent())
                .map(entry -> new ResponseEntity<>(
                        convertToResponse(entry),
                        HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private JournalEntryResponse convertToResponse(
            JournalEntry entry) {

        JournalEntryResponse response = new JournalEntryResponse();

        response.setId(entry.getId().toString());
        response.setTitle(entry.getTitle());
        response.setContent(entry.getContent());
        response.setDate(entry.getDate());
        response.setSentiment(entry.getSentiment());

        return response;
    }
}