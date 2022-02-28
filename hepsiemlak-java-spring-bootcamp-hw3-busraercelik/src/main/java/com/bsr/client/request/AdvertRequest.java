package com.bsr.client.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class AdvertRequest {
	private String title;
	private BigDecimal cost;
	private int duration;
	private boolean shouldHighlighted;
	private boolean isReviewed;
	private boolean isActive;
	private long propertyId;
}
