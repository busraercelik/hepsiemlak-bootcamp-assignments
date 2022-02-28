package com.bsr.repository;

import com.bsr.model.Message;
import com.bsr.model.Person;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepository {

    static List<Message> messages = new ArrayList<>();

    static {
        Person busra = createPersonData("busra", "busra@gmail.com");
        Person seller = createPersonData("seller", "seller@gmail.com");

        messages.add(createMessageData("I am interested in this house.",
                LocalDateTime.now(), busra, seller));
        messages.add(createMessageData("It's available for sale.",
                LocalDateTime.now(), seller, busra));
        messages.add(createMessageData("How much is it?",
                LocalDateTime.now(), busra, seller));
    }

    public Message saveMessage(Message message) {
        messages.add(message);
        return message;
    }

    public static Message createMessageData(String text, LocalDateTime time, Person person1, Person person2) {
       Message message = new Message();
       message.setText(text);
       message.setSentAt(time);
       message.setSentFrom(person1);
       message.setSentTo(person2);

       return message;
    }

    public static Person createPersonData(String fName, String email) {
        Person person = new Person();
        person.setEmail(email);
        person.setFirstName(fName);

        return person;
    }

    public List<Message> getAllMessages() {
        return messages;
    }

    public Message getMessageById(long id) {
       return messages.stream()
               .filter(message -> message.getId() == id)
               .findFirst().orElse(null);
    }
}
