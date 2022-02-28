package com.bsr.service;

import com.bsr.client.request.AddressRequest;
import com.bsr.client.request.AdvertRequest;
import com.bsr.client.request.BannerRequest;
import com.bsr.config.factory.PropertyFactory;
import com.bsr.dto.PropertyType;
import com.bsr.dto.response.AdvertResponseDTO;
import com.bsr.model.Advert;
import com.bsr.model.Person;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface IAdvertService {
    List<AdvertResponseDTO> getAllAdverts();

    AdvertResponseDTO saveAdvert(AdvertRequest request);

    AdvertResponseDTO getAdvertByAdvertId(int advertId);

    AtomicInteger ADVERT_NO = new AtomicInteger(1);

    default BannerRequest convertToBannerRequest(Advert advert) {
        BannerRequest bannerRequest = new BannerRequest();
        AddressRequest address = new AddressRequest();
        // set address fields from advert param
        address.setDistrict(advert.getProperty().getLocation().getDistrict());
        address.setCity(advert.getProperty().getLocation().getCity());
        // set bannerRequest fields from advert param
        bannerRequest.setAddress(address);
        bannerRequest.setAdvertNo(advert.getAdNo());
        bannerRequest.setPhone(advert.getUser().getMobileNo());
        bannerRequest.setDuration(advert.getDuration());

        return bannerRequest;
    }

    default AdvertResponseDTO convertToAdvertResponse(Advert savedAdvert) {
        AdvertResponseDTO response = new AdvertResponseDTO();
        response.setTitle(savedAdvert.getTitle());
        response.setCost(savedAdvert.getCost());
        response.setAdvertNo(savedAdvert.getAdNo());

        return response;
    }

    default Advert convertToAdvert(AdvertRequest request) {
        Advert advert = new Advert(PropertyFactory.getProperty(PropertyType.RESIDENTIAL), new Person(), new String[5]);
        advert.setAdNo(ADVERT_NO.incrementAndGet());
        advert.setTitle(request.getTitle());
        advert.setCost(request.getCost());

        return advert;
    }
}
