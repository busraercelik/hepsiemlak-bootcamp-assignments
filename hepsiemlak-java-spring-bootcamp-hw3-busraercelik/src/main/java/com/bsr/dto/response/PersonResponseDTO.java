package com.bsr.dto.response;

import com.bsr.model.Advert;
import lombok.Data;

import java.util.List;

@Data
public class PersonResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private List<Advert> favouriteAdverts;
}
