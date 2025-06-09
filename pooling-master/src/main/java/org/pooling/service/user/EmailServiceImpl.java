package org.pooling.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String receiver, String content, String subject) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("YOUR.EMAIL@gmail.com");
        mail.setTo(receiver);
        mail.setSubject(subject);
        mail.setText(content);
        javaMailSender.send(mail);
    }
}
