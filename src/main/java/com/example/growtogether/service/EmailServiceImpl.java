package com.example.growtogether.service;

import com.example.growtogether.constants.ExceptionMessages;
import com.example.growtogether.exception.EmailSendingFailureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    @Override
    public String sendInvitation(String email) {

        String uniqueKey = null;

        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            uniqueKey = UUID.randomUUID().toString();

            mailMessage.setFrom(sender);
            mailMessage.setTo(email);
            mailMessage.setText("Please find the unique key for registration: "+uniqueKey);
            mailMessage.setSubject("Invitation Key For Registration");

            javaMailSender.send(mailMessage);

        } catch (EmailSendingFailureException e) {
            throw new EmailSendingFailureException(ExceptionMessages.EMAIL_SENDING_FAILURE);
        }
        return uniqueKey;
    }
}
