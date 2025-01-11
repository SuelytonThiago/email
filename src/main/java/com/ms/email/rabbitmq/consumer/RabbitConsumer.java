package com.ms.email.rabbitmq.consumer;


import com.ms.email.domain.entities.EmailModel;
import com.ms.email.rest.dto.EmailDto;
import com.ms.email.rest.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {

    public  static final String QUEUE_MS_EMAIL = "MS_EMAIL";

    @Autowired
    private EmailService emailService;

   @RabbitListener(queues = QUEUE_MS_EMAIL)
    public void listen(EmailDto emailTo) {
       var email = new EmailModel();
       BeanUtils.copyProperties(emailTo, email);
       emailService.sendEmail(email);
   }
}
