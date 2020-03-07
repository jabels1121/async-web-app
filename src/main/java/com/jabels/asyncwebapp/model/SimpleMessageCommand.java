package com.jabels.asyncwebapp.model;

import lombok.Getter;

import java.time.LocalDateTime;

public class SimpleMessageCommand {

    @Getter
    private LocalDateTime created;
    @Getter
    private String message;

    public SimpleMessageCommand(String message) {
        this.created = LocalDateTime.now();
        this.message = message;
    }

}
