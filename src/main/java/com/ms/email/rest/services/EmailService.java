package com.ms.email.rest.services;
import com.ms.email.rest.dto.EmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(EmailDto dto) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(dto.getEmailTo());
        helper.setSubject(dto.getSubject());
        helper.setText(dto.getHtmlContent(), true);
        helper.setFrom(dto.getEmailFrom());

        emailSender.send(message);
    }
}