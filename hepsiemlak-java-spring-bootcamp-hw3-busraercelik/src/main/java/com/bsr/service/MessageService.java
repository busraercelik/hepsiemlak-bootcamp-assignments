package com.bsr.service;

import com.bsr.dto.MessageRequestDTO;
import com.bsr.dto.response.MessageResponseDTO;
import com.bsr.model.Message;
import com.bsr.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<MessageResponseDTO> getAllMessages() {
        List<MessageResponseDTO> responseDTOList = new ArrayList<>();

        for (Message message: messageRepository.getAllMessages()) {
            responseDTOList.add(convertToMessageResponseDTO(message));
        }
        return responseDTOList;
    }

    public MessageResponseDTO getMessageById(long id) {
       return convertToMessageResponseDTO(messageRepository.getMessageById(id));
    }

    public MessageResponseDTO saveMessage(MessageRequestDTO messageRequestDTO) {
        Message message = messageRepository.saveMessage(convertToMessage(messageRequestDTO));
        return convertToMessageResponseDTO(message);
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
