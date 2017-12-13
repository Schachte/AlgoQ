package com.algoq.algoq.services;

import com.algoq.algoq.Constants.Paths;
import com.algoq.algoq.models.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
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
    private String emailBody;

    public MailService() throws Exception {
    }

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
        helper.setText(emailBody, true);
        File file = new File("src/main/resources/" + timeStamp + ".pdf");
//        String absolutePath = file.getAbsolutePath();
//        helper.addAttachment("Solutions For " + timeStamp, new File(absolutePath));
        sender.send(message);
        logger.info("Message sent!");
    }

    /**
     * Sends bulk email for the daily algo question
     * @throws MessagingException
     */
    public void sendBulkEmail() throws Exception {
        logger.info("preparing message");
        List<Subscriber> subscriberList = algorithmService.getSubscribers();
        emailBody = this.emailBodyGenerator();

        subscriberList.forEach(s -> {
            try {
                sendEmail(s);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Dynamically load up html into string for content body
     * @return
     */
    public String emailBodyGenerator() throws Exception {
        try {
            File file = new File(Paths.PROBLEM_OF_THE_DAY);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            fileReader.close();
            logger.info("Completed HTML loader!");
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}
