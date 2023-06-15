package com.oopcourse.careernote.service.impl;

import com.oopcourse.careernote.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Slf4j
@Service(value="EmailService")
public class EmailServiceImpl implements EmailService {

    private Environment env;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(Environment env){
        this.env = env;
    }


    @Override
    public void sendEmail(String recipientEmail, String subject, String content) {
        log.info(this.getClass().getName()+".SendMail Start!");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(env.getProperty("spring.mail.username"));
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(content);

        javaMailSender.send(message);

        log.info(this.getClass().getName()+".SendMail End!");
    }

    public SimpleMailMessage setReceiver(String recipientEmail){
        log.info(this.getClass().getName()+".SendMail Start!");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(env.getProperty("spring.mail.username"));
        message.setTo(recipientEmail);
        return message;
    }

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }


}
