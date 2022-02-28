package com.bsr.emlakburada.queue;

import com.bsr.emlakburada.service.EmailMessageService;

public interface QueueService {
	
	void sendMessage(EmailMessageService email);

}
