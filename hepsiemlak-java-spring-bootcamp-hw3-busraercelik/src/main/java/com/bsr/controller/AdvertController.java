package com.bsr.controller;

import com.bsr.client.request.AdvertRequest;
import com.bsr.dto.response.AdvertResponseDTO;
import com.bsr.model.Advert;
import com.bsr.service.AdvertService;
import com.bsr.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdvertController {

	@Autowired
	private AdvertService advertService;

	@Autowired
	private PersonService personService;

	@GetMapping(value = "/adverts")
	public ResponseEntity<List<AdvertResponseDTO>> getAllAdvert() {
		return new ResponseEntity<>(advertService.getAllAdverts(), HttpStatus.OK);
	}

	@PostMapping(value = "/advert")
	public ResponseEntity<AdvertResponseDTO> createAdvert(@RequestBody AdvertRequest request) {
		return new ResponseEntity<>(advertService.saveAdvert(request), HttpStatus.CREATED);
	}

	@GetMapping(value = "/advert/{advertNo}")
	public ResponseEntity<AdvertResponseDTO> getAdvertByAdvertId(@PathVariable(required = false) int advertNo) {
		return new ResponseEntity<>(advertService.getAdvertByAdvertId(advertNo), HttpStatus.OK);
	}

	@GetMapping(value = "/adverts/{personId}")
	public ResponseEntity<List<AdvertResponseDTO>> getFavouriteAdverts(@PathVariable long personId) {
		List<Advert> favouriteAdverts = personService.showFavouriteAdverts(personId).getFavouriteAdverts();
		List<AdvertResponseDTO> advertResponseDTOS = new ArrayList<>();
		for (Advert advert : favouriteAdverts) {
			advertResponseDTOS.add(advertService.convertToAdvertResponse(advert));
		}
		return new ResponseEntity<>(advertResponseDTOS , HttpStatus.OK);
	}

}
