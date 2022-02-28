package com.bsr.queue;

import com.bsr.service.EmailMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * JmsTemplate will be used for sending messages to the Topic/Queue.
 * */
@Component
@Slf4j
public class ActiveMQCreator implements QueueService{

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	/**
	 * destination is the name of the queue we create
	 * For sending custom messages we can make use of JmsTemplate.convertAndSend().
	 * This should successfully publish our messages to the ActiveMQ Topic.
	 */
	@Override
	public void sendMessage(EmailMessageService email) {
		this.jmsMessagingTemplate.convertAndSend("my-activemq", email.getEmail());
		log.info("Message has been put to queue by sender");
	}
}
