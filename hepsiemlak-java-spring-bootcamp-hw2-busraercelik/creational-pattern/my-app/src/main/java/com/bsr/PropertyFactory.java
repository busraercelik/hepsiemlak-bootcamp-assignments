package com.bsr;

import com.bsr.dto.PropertyType;
import com.bsr.model.Commercial;
import com.bsr.model.Land;
import com.bsr.model.Property;
import com.bsr.model.Residential;

public class PropertyFactory {

    public static Property getProperty(PropertyType propertyType) {
        if (propertyType == null) {
            return null;
        }

        if (propertyType == PropertyType.LAND) {
            return new Land();
        }

        if (propertyType == PropertyType.COMMERCIAL) {
            return new Commercial();
        }

        if (propertyType == PropertyType.RESIDENTIAL) {
            return new Residential();
        }

        return null;
    }
}
