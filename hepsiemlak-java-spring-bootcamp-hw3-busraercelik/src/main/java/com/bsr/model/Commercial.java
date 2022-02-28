package com.bsr.model;

import com.bsr.dto.CommercialType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Commercial extends Property{

    private String category;

    private int dues;

    @Override
    public List<CommercialType> getTypes() {
        return Arrays.asList(CommercialType.values());
    }
}
