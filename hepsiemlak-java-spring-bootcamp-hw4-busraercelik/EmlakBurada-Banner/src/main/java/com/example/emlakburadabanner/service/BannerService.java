package com.example.emlakburadabanner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.emlakburadabanner.model.Banner;
import com.example.emlakburadabanner.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emlakburada.dto.request.BannerRequest;
import emlakburada.dto.response.BannerResponse;

@Service
public class BannerService {

	@Autowired
	private BannerRepository repository;

	public List<BannerResponse> getAllBanners() {
		return repository.findAll().stream()
				.map(this::convertToBannerResponse)
				.collect(Collectors.toList());
	}

	public void saveBanner(BannerRequest request) {
		repository.save(convertToBanner(request));
	}

	private BannerResponse convertToBannerResponse(Banner banner) {
		BannerResponse response = new BannerResponse();
		response.setAdvertNo(banner.getAdvertNo());
		response.setPhone(banner.getPhone());
		response.setTotal(banner.getTotal());
		return response;
	}

	private Banner convertToBanner(BannerRequest request) {
		Banner banner = new Banner();
		banner.setAdvertNo(request.getAdvertNo());
		banner.setPhone(request.getPhone());
		banner.setTotal(request.getTotal());
		return banner;
	}

}
