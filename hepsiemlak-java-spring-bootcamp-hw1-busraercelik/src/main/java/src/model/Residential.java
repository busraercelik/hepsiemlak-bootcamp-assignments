package src.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Residential extends Property{

    private String noOfRoom;

    private int noOfBath;

    private int floor;

    private int buildingAge;

    private boolean isEligibleForCredit;
}
