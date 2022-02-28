package com.bsr.emlakburada.service;

import com.bsr.emlakburada.client.request.BannerRequest;
import com.bsr.emlakburada.client.response.BannerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "emlak-banner-service", url = "http://localhost:8081")
public interface EmlakBannerService {

    @PostMapping(value = "/banner")
    BannerResponse saveBanner(@RequestBody BannerRequest request);
}
