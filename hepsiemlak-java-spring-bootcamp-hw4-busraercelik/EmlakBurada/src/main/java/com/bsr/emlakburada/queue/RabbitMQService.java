package com.bsr.emlakburada.queue;


import com.bsr.emlakburada.config.RabbitMQConfig;
import com.bsr.emlakburada.service.EmailMessageService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQService implements QueueService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Autowired
	private RabbitMQConfig config;

	@Override
	public void sendMessage(EmailMessageService message) {
		rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), message);

	}

}
