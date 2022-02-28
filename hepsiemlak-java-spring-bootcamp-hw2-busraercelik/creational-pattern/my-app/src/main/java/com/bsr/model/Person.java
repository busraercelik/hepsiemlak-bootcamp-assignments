package com.bsr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNo;

    private List<Advert> favourites;
}
