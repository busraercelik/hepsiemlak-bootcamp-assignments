package src.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Property {

    private long id;

    private int cost;

    private float grossSquareMeter;

    private boolean isForSale;

    private Location location;
}
