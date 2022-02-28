package com.bsr.service;

import com.bsr.client.BannerClient;
import com.bsr.client.request.AddressRequest;
import com.bsr.client.request.AdvertRequest;
import com.bsr.client.request.BannerRequest;
import com.bsr.config.factory.PropertyFactory;
import com.bsr.dto.PropertyType;
import com.bsr.dto.response.AdvertResponseDTO;
import com.bsr.model.Advert;
import com.bsr.model.Person;
import com.bsr.queue.QueueService;
import com.bsr.repository.AdvertRepository;
import com.bsr.repository.DbConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertService {

	@Autowired
	private AdvertRepository advertRepository;

	@Autowired
	@Qualifier(value = "jdbcConnectionRepository")
	private DbConnectionRepository dbConn;

	@Autowired
	private PersonService userService;

	@Autowired
	private BannerClient bannerClient;

	@Autowired
	private EmlakBannerService emlakBannerService;

	@Autowired
	@Qualifier(value = "activeMQCreator")
	private QueueService queueService;

	private int advertNo = 38164784;
	private int adNo = 1;

	public List<AdvertResponseDTO> getAllAdverts() {
		List<AdvertResponseDTO> advertList = new ArrayList<>();

		for (Advert advert : advertRepository.fetchAllAdverts()) {
			advertList.add(convertToAdvertResponse(advert));
		}
		return advertList;
	}

	// after advert is saved send mail to queue
	public AdvertResponseDTO saveAdvert(AdvertRequest request) {
		Advert savedAdvert = advertRepository.saveAdvert(convertToAdvert(request));
		EmailMessageService emailMessage = new EmailMessageService("cemdrman@gmail.com");
		queueService.sendMessage(emailMessage);
		// save banner with rest template
//		bannerClient.saveBanner();

		// save banner with feign client
		emlakBannerService.saveBanner(prepareSaveBannerRequest());

		return convertToAdvertResponse(savedAdvert);
	}

	private BannerRequest prepareSaveBannerRequest() {
		BannerRequest request = new BannerRequest();
		request.setAdvertNo(adNo++);
		request.setPhone("12345");
		request.setDuration(1);
		request.setAddress(new AddressRequest("istanbul", "Kadikoy", "home address"));

		return request;
	}


	public AdvertResponseDTO convertToAdvertResponse(Advert savedAdvert) {
		AdvertResponseDTO response = new AdvertResponseDTO();
		response.setTitle(savedAdvert.getTitle());
		response.setCost(savedAdvert.getCost());
		response.setAdvertNo(savedAdvert.getAdNo());

		return response;
	}

	public Advert convertToAdvert(AdvertRequest request) {
		Advert advert = new Advert(PropertyFactory.getProperty(PropertyType.RESIDENTIAL), new Person(), new String[5]);
		advert.setAdNo(advertNo++);
		advert.setTitle(request.getTitle());
		advert.setCost(request.getCost());

		return advert;
	}

	public AdvertResponseDTO getAdvertByAdvertId(int advertId) {
		Advert advert = advertRepository.findAdvertByAdvertId(advertId);
		return convertToAdvertResponse(advert);
	}

}
