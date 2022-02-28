package com.bsr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Property {

    private long id;

    private int cost;

    private float grossSquareMeter;

    private boolean isForSale;

    private Location location;

    public abstract <T> List<T> getTypes();
}
