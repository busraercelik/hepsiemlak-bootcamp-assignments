package com.bsr.emlakburada.config;

import javax.jms.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;


@Configuration
public class ActiveMQConfig {

    @Value("${spring.activemq.user}")
    private String queueName;

    @Value("${spring.activemq.password}")
    private String password;

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(ConnectionFactory connectionFactory) {
        return new JmsMessagingTemplate(connectionFactory);
    }
}
