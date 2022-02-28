package com.bsr.model;

import com.bsr.dto.ResidentialType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Residential extends Property{

    private String noOfRoom;

    private int noOfBath;

    private int floor;

    private int buildingAge;

    private boolean isEligibleForCredit;

    @Override
    public List<ResidentialType> getTypes() {
        return Arrays.asList(ResidentialType.values());
    }
}
