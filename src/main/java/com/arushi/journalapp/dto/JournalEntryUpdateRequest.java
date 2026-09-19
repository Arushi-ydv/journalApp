package com.arushi.journalapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JournalEntryUpdateRequest {

    private String title;
    private String content;
}