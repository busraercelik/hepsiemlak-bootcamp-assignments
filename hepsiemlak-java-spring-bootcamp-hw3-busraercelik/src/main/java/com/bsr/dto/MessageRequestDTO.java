package com.bsr.dto;

import com.bsr.model.Person;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageRequestDTO {
    private String text;
    private LocalDateTime sentAt;
    private Person sentFrom;
    private Person sentTo;
}
