package com.arushi.journalapp.controller;

import com.arushi.journalapp.dto.JournalEntryRequest;
import com.arushi.journalapp.dto.JournalEntryResponse;
import com.arushi.journalapp.dto.JournalEntryUpdateRequest;
import com.arushi.journalapp.entity.JournalEntry;
import com.arushi.journalapp.entity.User;
import com.arushi.journalapp.service.JournalEntryService;
import com.arushi.journalapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    private String getLoggedInUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping
    public ResponseEntity<List<JournalEntryResponse>> getAllJournalEntriesOfUser() {

        String userName = getLoggedInUserName();
        User user = userService.findByUserName(userName);

        List<JournalEntryResponse> responses = user.getJournalEntries()
                .stream()
                .map(entry -> {
                    JournalEntryResponse response = new JournalEntryResponse();

                    response.setId(entry.getId().toString());
                    response.setTitle(entry.getTitle());
                    response.setContent(entry.getContent());
                    response.setDate(entry.getDate());
                    response.setSentiment(entry.getSentiment());

                    return response;
                })
                .toList();

        if(!responses.isEmpty()){
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntryResponse> createEntry(
            @Valid @RequestBody JournalEntryRequest request) {
        try {
            String userName = getLoggedInUserName();

            JournalEntry journalEntry = new JournalEntry();
            journalEntry.setTitle(request.getTitle());
            journalEntry.setContent(request.getContent());

            journalEntryService.saveEntry(journalEntry, userName);

            JournalEntryResponse response = new JournalEntryResponse();
            response.setId(journalEntry.getId().toString());
            response.setTitle(journalEntry.getTitle());
            response.setContent(journalEntry.getContent());
            response.setDate(journalEntry.getDate());
            response.setSentiment(journalEntry.getSentiment());

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntryResponse> getJournalEntryById(
            @PathVariable Long myId) {

        String userName = getLoggedInUserName();
        User user = userService.findByUserName(userName);

        List<JournalEntry> collect = user.getJournalEntries()
                .stream()
                .filter(x -> x.getId().equals(myId))
                .collect(Collectors.toList());

        if(!collect.isEmpty()) {

            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);

            if(journalEntry.isPresent()) {

                JournalEntry entry = journalEntry.get();

                JournalEntryResponse response = new JournalEntryResponse();

                response.setId(entry.getId().toString());
                response.setTitle(entry.getTitle());
                response.setContent(entry.getContent());
                response.setDate(entry.getDate());
                response.setSentiment(entry.getSentiment());

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable Long myId) {

        String userName = getLoggedInUserName();
        boolean removed = journalEntryService.deleteById(myId, userName);
        if(removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntryResponse> updateJournalById(
            @PathVariable Long myId,
            @RequestBody JournalEntryUpdateRequest request) {

        String userName = getLoggedInUserName();
        User user = userService.findByUserName(userName);

        // Check whether the journal belongs to the logged-in user
        boolean exists = user.getJournalEntries()
                .stream()
                .anyMatch(entry -> entry.getId().equals(myId));

        if (!exists) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<JournalEntry> optionalJournal = journalEntryService.findById(myId);

        if (optionalJournal.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        JournalEntry oldJournal = optionalJournal.get();

        if (request.getTitle() != null && !request.getTitle().isBlank()) {
            oldJournal.setTitle(request.getTitle());
        }

        if (request.getContent() != null && !request.getContent().isBlank()) {
            oldJournal.setContent(request.getContent());
        }

        journalEntryService.saveEntry(oldJournal);

        JournalEntryResponse response = new JournalEntryResponse();
        response.setId(oldJournal.getId().toString());
        response.setTitle(oldJournal.getTitle());
        response.setContent(oldJournal.getContent());
        response.setDate(oldJournal.getDate());
        response.setSentiment(oldJournal.getSentiment());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
