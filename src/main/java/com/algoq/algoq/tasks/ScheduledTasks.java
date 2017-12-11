package com.algoq.algoq.tasks;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.algoq.algoq.services.AlgorithmService;
import com.algoq.algoq.services.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired // = new MailService();
    private MailService mService;

    @Autowired
    private AlgorithmService algorithmService;

    //TODO: Set this to a 24 hour schedule so users aren't spammed
//    @Scheduled(fixedRate = 10000)
//    public void subscriberNotifier() throws MessagingException {
//        log.info("Initializing the email sender");
//        mService.sendBulkEmail();
//    }

}
