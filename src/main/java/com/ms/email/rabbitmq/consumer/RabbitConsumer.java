package com.ms.email.rabbitmq.consumer;
import com.ms.email.rabbitmq.config.RabbitMQValues;
import com.ms.email.rest.dto.EmailDto;
import com.ms.email.rest.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {

    @Autowired
    private EmailService emailService;

   @RabbitListener(queues = RabbitMQValues.QUEUE_MS_EMAIL)
    public void listen(EmailDto emailTo) throws MessagingException {
       emailService.sendEmail(emailTo);
   }
}
