package com.bsr.controller;

import com.bsr.dto.MessageRequestDTO;
import com.bsr.dto.response.MessageResponseDTO;
import com.bsr.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping(value = "/messages")
    public ResponseEntity<List<MessageResponseDTO>> getAllMessages() {
        return new ResponseEntity<>(messageService.getAllMessages(), HttpStatus.OK);
    }

    @GetMapping(value = "message/{messageId}")
    public ResponseEntity<MessageResponseDTO> getMessageById(@PathVariable long messageId) {
        return new ResponseEntity<>(messageService.getMessageById(messageId), HttpStatus.OK);
    }

    @PostMapping(value = "/message")
    public ResponseEntity<MessageResponseDTO> saveMessage(@RequestBody MessageRequestDTO messageRequestDTO) {
        return new ResponseEntity<>(messageService.saveMessage(messageRequestDTO), HttpStatus.CREATED);
    }
}
