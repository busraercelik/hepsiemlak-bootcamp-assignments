package com.bsr.queue;


import com.bsr.service.EmailMessageService;

public interface QueueService {
	
	void sendMessage(EmailMessageService email);

}
