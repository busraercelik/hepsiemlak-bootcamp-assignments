package com.bsr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Advert {

    private int adNo;
    private String description;
    private Property property;
    private String title;
    private Person user; // hem bireysel & kurumsal
    private String[] imageList = new String[5];
    private BigDecimal cost;
    private int duration;
    private boolean shouldHighlighted = false;
    private boolean isReviewed = false;
    private LocalDate postedDate;
    private boolean isActive;
    private Person postedBy;

    public Advert(Property realEstate, Person user, String[] imageList) {
        this.property = realEstate;
        this.user = user;
        this.imageList = imageList;
    }
}
