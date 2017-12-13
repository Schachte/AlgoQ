package com.algoq.algoq.controller;

import com.algoq.algoq.models.POTD;
import com.algoq.algoq.models.POTDResources;
import com.algoq.algoq.services.PDFService;
import com.algoq.algoq.services.TemplateGenerationService;
import com.itextpdf.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Controller
public class TemplateController {

    @Autowired
    private TemplateGenerationService tempGen;

    private static final Logger log = LoggerFactory.getLogger(AlgorithmController.class);

    /**
     * Generate the problem of the day
     * @throws IOException
     */
    @RequestMapping("/creation")
    @ResponseBody
    public void problemGenerator() throws IOException, DocumentException {

        POTD prob = new POTD();
        POTDResources r1 = new POTDResources("Google", "http://google.com");
        prob.setSubject("Problem of the Day - " + new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date()));
        prob.setProblemTitle("Balancing A Binary Search Tree");
        prob.setProblemDescription("Fucking code");
        prob.setResources(new ArrayList<POTDResources>(){{ add(r1); }});

        tempGen.generateProblemOfTheDay(prob);
    }
}

