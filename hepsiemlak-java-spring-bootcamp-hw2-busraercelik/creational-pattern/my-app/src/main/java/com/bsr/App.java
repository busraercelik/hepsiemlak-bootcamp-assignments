package com.bsr;

import com.bsr.dto.PropertySearchDTO;
import com.bsr.dto.PropertyType;
import com.bsr.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class App
{
    public static void main(String[] args) {
//        question1();
//        question2();
//        question3();
        callPropertyFactory();
    }

    private static void callPropertyFactory() {
        PropertyFactory factory = new PropertyFactory();
        System.out.println("Enter the name of property which the property types you want to list: ");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PropertyType propertyType = null;

        try {
            final String propertyTypeStr = bf.readLine();
            propertyType = Arrays.stream(PropertyType.values())
                    .filter(propertyType1 -> propertyType1.toString().equalsIgnoreCase(propertyTypeStr))
                    .findAny()
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Property property = factory.getProperty(propertyType);
        System.out.println(property.getTypes().toString());
    }

    static List<Advert> adverts = new ArrayList<>();
    static List<Message> messages = new ArrayList<>();
    static List<Person> persons = new ArrayList<>();

    static void createAdvertData() {
        // 1
        Advert advert1 = new Advert();
        advert1.setTitle("Very Nice Building, Comfy");

        Residential building1 = new Residential();
        building1.setCost(4500);

        Location istanbul = new Location();
        istanbul.setCity("ISTANBUL");

        building1.setLocation(istanbul);
        building1.setNoOfRoom("2+1");

        advert1.setProperty(building1);
        adverts.add(advert1);

        System.out.println(advert1);

        // 2
        Advert advert2 = new Advert();
        advert1.setTitle("Near metro!");

        Residential building2 = new Residential();
        building2.setCost(6000);
        building2.setLocation(istanbul);
        building2.setNoOfRoom("3+1");

        advert2.setProperty(building2);
        adverts.add(advert2);

    }

    static void question1() {
        createAdvertData();
        PropertySearchDTO search1 = PropertySearchDTO.from().maxPrice(8000).minPrice(0)
                .propertyType(PropertyType.RESIDENTIAL)
                .noOfRooms(Arrays.asList("2+1", "3+1"))
                .city("ISTANBUL");
        System.out.println(searchPropertyByFilter(search1));
    }

    static List<Advert> searchPropertyByFilter(PropertySearchDTO searchDTO) {
        return adverts.stream().filter(advert -> advert.getProperty().getCost() < searchDTO.getMaxPrice()
                        && advert.getProperty().getCost() > searchDTO.getMinPrice())
                .filter(advert -> advert.getProperty() instanceof Residential)
                .filter(advert -> searchDTO.getNoOfRooms().contains(((Residential) advert.getProperty()).getNoOfRoom()))
                .filter(advert -> advert.getProperty().getLocation().getCity().equalsIgnoreCase("Istanbul"))
                .collect(Collectors.toList());
    }

    static void createMessageAndPersonData() {
        Person busra = new Person();
        Person agent = new Person();
        busra.setFirstName("busra");
        busra.setLastName("ercelik");
        agent.setFirstName("john");
        agent.setLastName("smith");

        Message message1 = new Message();
        Message message2 = new Message();
        Message message3 = new Message();
        message1.setSentFrom(busra);
        message1.setSentTo(agent);
        message1.setText("I am interested in this house.");
        message1.setSentAt(LocalDateTime.now());

        message2.setSentFrom(agent);
        message2.setSentTo(busra);
        message2.setText("It's available for sale.");
        message2.setSentAt(LocalDateTime.now());

        message3.setSentFrom(busra);
        message3.setSentTo(agent);
        message3.setText("How much is it?");
        message3.setSentAt(LocalDateTime.now());

        messages.addAll(Arrays.asList(message1,message2,message3));
    }

    static void question2() {
        System.out.println();
        createMessageAndPersonData();
        System.out.println(messages.stream()
                .map(message -> message.getSentFrom().getFirstName() +" : "+ message.getText())
                .collect(Collectors.toList()));
    }

    static void prepareFavourites() {
        Person ayse = new Person();
        ayse.setFirstName("ayse");
        ayse.setLastName("sari");
        ayse.setEmail("ayse.sari@gmail.com");
        ayse.setFavourites(adverts);

        persons.add(ayse);

        Person busra = new Person();
        busra.setFirstName("busra");
        busra.setLastName("ercelik");
        busra.setEmail("busra@gmail.com");
        busra.setFavourites(Collections.singletonList(adverts.get(0)));

        persons.add(busra);
    }

    static void question3() {
        System.out.println();
        prepareFavourites();
        showFavourites("ayse.sari@gmail.com");
    }

    static void showFavourites(String email) {
        System.out.println("Showing favourites of user: " + email);
        System.out.println(persons.stream()
                .filter(person -> person.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList()));
    }


}
