package org.pooling.service.user;

public interface EmailService {
    void sendEmail(String receiver, String content, String subject);
}
