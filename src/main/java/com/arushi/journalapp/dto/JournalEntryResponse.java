package com.arushi.journalapp.dto;

import com.arushi.journalapp.enums.Sentiment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JournalEntryResponse {

    private String id;
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;
}