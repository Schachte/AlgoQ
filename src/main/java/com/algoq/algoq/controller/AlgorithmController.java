package com.algoq.algoq.controller;

import com.algoq.algoq.models.Subscriber;
import com.algoq.algoq.services.AlgorithmService;
import com.algoq.algoq.services.MailService;
import com.algoq.algoq.services.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MailService mailService;

    @Autowired
    private PDFService pdfService;

    @RequestMapping(method = RequestMethod.GET, value = "/subscribers")
    public List<Subscriber> getAllSubscribers() {
        return aService.getSubscribers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/subscribers")
    public void addSubscriber(@RequestBody Subscriber sub) {
        log.info("Adding user.. ");
        aService.addSubscriber(sub);
    }

    @RequestMapping(value="/email")
    public void sendEmail() throws MessagingException {
        mailService.sendBulkEmail();
    }
}
