package org.pooling.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String receiver, String content, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@pooling.org");
        message.setTo(receiver);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}