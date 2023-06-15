package com.oopcourse.careernote.service;

public interface EmailService {
    void sendEmail(String recipientEmail,String subject,String content);
}
