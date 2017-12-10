package com.algoq.algoq.controllers;

import com.algoq.algoq.models.Subscriber;
import com.algoq.algoq.services.AlgorithmService;
import com.algoq.algoq.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.util.List;

@RestController
public class AlgorithmController {

    private static final Logger log = LoggerFactory.getLogger(AlgorithmController.class);
    @Autowired
    private AlgorithmService aService;

    @Autowired
    @Qualifier("MailSender")
    private MailService mSender;

    @RequestMapping(method = RequestMethod.GET, value = "/subscribers")
    public List<Subscriber> getAllSubscribers() {
        return aService.getSubscribers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/subscribers")
    public void addSubscriber(@RequestBody Subscriber sub) {
        log.info("Adding user.. ");
        aService.addSubscriber(sub);
    }

    @RequestMapping(value = "/subscribers/{emailAddress:.+}")
    public List<Subscriber> getUserByEmail(@PathVariable("emailAddress") String emailAddress) {
        return aService.getSubscriber(emailAddress);
    }

    @RequestMapping(value = "/send")
    public void sendEmail() throws MessagingException {
        String from = "mister@shell.com";
        String to = "quibblehack@gmail.com";
        String subject = "JavaMailSender";
        String body = "Just-Testing!";

        mSender.sendMail(from, to, subject, body);
    }
}
