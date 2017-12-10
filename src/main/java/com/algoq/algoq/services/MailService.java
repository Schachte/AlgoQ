package com.algoq.algoq.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component("MailSender")
public class MailService {

    @Autowired
    JavaMailSenderImpl sender;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void sendMail(String from, String to, String subject, String body) throws MessagingException {

        logger.info("preparing message");

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);

        helper.setText("<html><body><b>testing bold</b><h1>HEADER</h1></body></html>", true);

        sender.send(message);
        logger.info("Message sent!");
    }
}
