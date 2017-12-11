package com.algoq.algoq.services;

import com.algoq.algoq.models.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

@Component("MailSender")
public class MailService {

    @Autowired
    JavaMailSenderImpl sender;

    @Autowired
    AlgorithmService algorithmService;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date());

    //TODO: Make this parallelized
    /**
     * Process the individual email message
     * @param subscriber
     * @throws MessagingException
     */

    public void sendEmail(Subscriber subscriber) throws MessagingException, IOException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        logger.info("Sending message to " + subscriber.getEmailAddress());
        helper.setTo(subscriber.getEmailAddress());
        helper.setText("<html>" +
                        "<body><h1>AlgoQ Algorithm of the Day</h1></br>" +
                        "Question: " +
                        "</body></html>",
                true);
        File file = new File("src/main/resources/" + timeStamp + ".pdf");
        String absolutePath = file.getAbsolutePath();
        helper.addAttachment("Solutions For " + timeStamp, new File(absolutePath));
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
