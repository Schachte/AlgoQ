package com.algoq.algoq.services;

import com.algoq.algoq.models.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Component("MailSender")
public class MailService {

    @Autowired
    JavaMailSenderImpl sender;

    @Autowired
    AlgorithmService algorithmService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    //TODO: Make this parallelized
    /**
     * Process the individual email message
     * @param subscriber
     * @throws MessagingException
     */

    public void sendEmail(Subscriber subscriber) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        logger.info("Sending message to " + subscriber.getEmailAddress());
        helper.setTo(subscriber.getEmailAddress());
        helper.setText("<html><body><b>testing bold</b><h1>"+ subscriber.getName() +"</h1></body></html>", true);
        sender.send(message);
        logger.info("Message sent!");
    }

    /**
     * Sends bulk email for the daily algo question
     * @throws MessagingException
     */
    public void sendBulkEmail() throws MessagingException {
        logger.info("preparing message");
        List<Subscriber> subscriberList = algorithmService.getSubscribers();

        subscriberList.forEach(s -> {
            try {
                sendEmail(s);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }
}
