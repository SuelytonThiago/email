package com.ms.email.rest.services;
import com.ms.email.rest.dto.EmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
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

        if (!isValidEmail(dto.getEmailTo())) {
            throw new IllegalArgumentException("Endereço de e-mail inválido: " + dto.getEmailTo());
        }

        if (!isValidEmail(dto.getEmailFrom())) {
            throw new IllegalArgumentException("Endereço de e-mail do remetente inválido: " + dto.getEmailFrom());
        }

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(dto.getEmailTo());
        helper.setSubject(dto.getSubject());
        helper.setText(dto.getHtmlContent(), true);
        helper.setFrom(dto.getEmailFrom());

        emailSender.send(message);
    }

    private boolean isValidEmail(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        } catch (jakarta.mail.internet.AddressException ex) {
            return false;
        }
    }
}