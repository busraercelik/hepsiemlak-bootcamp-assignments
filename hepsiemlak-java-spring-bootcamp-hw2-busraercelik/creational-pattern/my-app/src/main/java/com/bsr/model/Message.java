package com.bsr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    private long id;

    private String text;

    private LocalDateTime sentAt;

    private Person sentFrom;

    private Person sentTo;
}
