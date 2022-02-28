package com.bsr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Advert extends Identifiable{
    private int adNo;

    private String title;

    private String description;

    private LocalDate postedDate;

    private Property property;

    private Person postedBy;
}
