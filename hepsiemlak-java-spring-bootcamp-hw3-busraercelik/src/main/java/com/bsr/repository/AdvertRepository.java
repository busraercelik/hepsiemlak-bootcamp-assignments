package com.bsr.repository;

import com.bsr.config.factory.PropertyFactory;
import com.bsr.dto.PropertyType;
import com.bsr.model.Advert;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdvertRepository {

	private static List<Advert> advertList = new ArrayList<>();

	static {
		advertList.add(createAdvert(38164780, "Sahibinden Acil Satılık"));
		advertList.add(createAdvert(38164781, "Dosta GİDERRR ACİLLL!!!"));
		advertList.add(createAdvert(38164782, "Metroya Koşarak 5 dk"));
		advertList.add(createAdvert(38164783, "Öğrenciye ve Bekar uygun"));
	}

	public List<Advert> fetchAllAdverts() {
		return advertList;
	}

	private static Advert createAdvert(int advertNo, String baslik) {
		Advert advert = new Advert();
		advert.setAdNo(advertNo);
		advert.setTitle(baslik);
		advert.setProperty(PropertyFactory.getProperty(PropertyType.COMMERCIAL));
		advert.setActive(true);
		advert.setImageList(getImageList());

		return advert;
	}

	private static String[] getImageList() {
		String[] images = new String[5];
		images[0] = "https://hecdnw01.hemlak.com/ds01/4/4/9/0/2/3/8/3/81d2e088-a551-485d-b2e9-664cc9200cdc.jpg";
		images[1] = "https://hecdnw01.hemlak.com/ds01/4/4/9/0/2/3/8/3/81d2e088-a551-485d-b2e9-664cc9200cdc.jpg";

		return images;
	}

	public Advert saveAdvert(Advert advert) {
		advertList.add(advert);
		System.out.println(advert.toString());

		return advert;
	}

	public Advert findAdvertByAdvertId(int advertNo) {
		return advertList.stream()
				.filter(advert -> advert.getAdNo() == advertNo)
				.findAny().orElse(new Advert());
	}

}
