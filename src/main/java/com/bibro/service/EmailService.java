package com.bibro.service;

import com.bibro.domain.user.RegistrationToken;
import com.bibro.domain.user.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegistrationToken(RegistrationToken registrationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(registrationToken.getUser().getEmail());
        message.setSubject("aktywuj konto");
        message.setText("http://localhost:8080/confirm-registration?token=" + registrationToken.getToken());
        mailSender.send(message);
    }
}