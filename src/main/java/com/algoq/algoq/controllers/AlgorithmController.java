package com.algoq.algoq.controllers;

import com.algoq.algoq.models.Subscriber;
import com.algoq.algoq.services.AlgorithmService;
import com.algoq.algoq.services.MailService;
import com.algoq.algoq.services.PDFService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
public class AlgorithmController {

    private static final Logger log = LoggerFactory.getLogger(AlgorithmController.class);
    @Autowired
    private AlgorithmService aService;

    @Autowired
    @Qualifier("MailSender")
    private MailService mSender;

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

    @RequestMapping(value = "/subscribers/{emailAddress:.+}")
    public List<Subscriber> getUserByEmail(@PathVariable("emailAddress") String emailAddress) {
        return aService.getSubscriber(emailAddress);
    }

    @RequestMapping(value = "/pdf")
    public void GenPDF() throws IOException, DocumentException {
        log.info("Generaeting the PDF");
        pdfService.generatePDF();
    }
}
