package com.bsr.emlakburada.dto.response;

import com.bsr.emlakburada.model.Person;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MessageResponseDTO {
    private String text;
    private LocalDateTime sentAt;
    private Person sentFrom;
    private Person sentTo;
}
