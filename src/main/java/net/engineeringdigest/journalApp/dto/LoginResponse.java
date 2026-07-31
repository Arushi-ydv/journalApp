package net.engineeringdigest.journalApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String message;

    private String token;

    private String type;
}
