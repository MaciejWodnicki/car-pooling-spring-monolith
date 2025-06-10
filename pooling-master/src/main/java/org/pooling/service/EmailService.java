package org.pooling.service;

public interface EmailService {
    void sendEmail(String receiver, String content, String subject);
}
