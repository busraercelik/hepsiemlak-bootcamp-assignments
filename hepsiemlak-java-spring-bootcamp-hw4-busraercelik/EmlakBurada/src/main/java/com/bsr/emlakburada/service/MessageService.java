package com.bsr.emlakburada.service;

import com.bsr.emlakburada.dto.MessageRequestDTO;
import com.bsr.emlakburada.dto.response.MessageResponseDTO;
import com.bsr.emlakburada.model.Message;
import com.bsr.emlakburada.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<MessageResponseDTO> getAllMessages() {
       return messageRepository.findAll()
               .stream()
               .map(this::convertToMessageResponseDTO)
               .collect(Collectors.toList());
    }

    public MessageResponseDTO getMessageById(long id) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        return messageOptional.map(this::convertToMessageResponseDTO).orElse(null);
    }

    public void saveMessage(MessageRequestDTO messageRequestDTO) {
        messageRepository.save(convertToMessage(messageRequestDTO));
    }

    private MessageResponseDTO convertToMessageResponseDTO(Message message) {
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        modelMapper.map(message, messageResponseDTO);

        return messageResponseDTO;
    }

    private Message convertToMessage(MessageRequestDTO messageRequestDTO) {
        Message message = new Message();
        modelMapper.map(messageRequestDTO, message);

        return message;
    }

}
