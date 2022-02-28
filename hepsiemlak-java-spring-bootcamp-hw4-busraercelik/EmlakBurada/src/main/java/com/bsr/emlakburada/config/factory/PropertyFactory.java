package com.bsr.emlakburada.config.factory;

import com.bsr.emlakburada.dto.PropertyType;
import com.bsr.emlakburada.model.Commercial;
import com.bsr.emlakburada.model.Land;
import com.bsr.emlakburada.model.Property;
import com.bsr.emlakburada.model.Residential;

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
