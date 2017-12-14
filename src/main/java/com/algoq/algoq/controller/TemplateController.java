package com.algoq.algoq.controller;

import com.algoq.algoq.models.POTD;
import com.algoq.algoq.models.POTDResources;
import com.algoq.algoq.services.MailService;
import com.algoq.algoq.services.TemplateGenerationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TemplateController {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateGenerationService tempService;

    private static final Logger log = LoggerFactory.getLogger(AlgorithmController.class);

    @RequestMapping(value = "/generatepotd", method = RequestMethod.POST)
    @ResponseBody
    public void generatePOTD(@RequestBody POTD problem) throws Exception {
        mailService.sendBulkEmail(problem);
    }

    @RequestMapping(value = "/potdForm", method=RequestMethod.POST)
    @ResponseBody
    public String formPOTDGeneration(@RequestParam("potd_title") String potd_title,
                                     @RequestParam("potd_description") String potd_description,
                                     @RequestParam("potd_code") Optional<String> potd_code,
                                     @RequestParam("potd_resources") String resources) throws IOException {

        ArrayList<POTDResources> linkResources = new ObjectMapper().readValue(resources, new TypeReference<ArrayList<POTDResources>>(){});

        POTD problemOfTheDay = (potd_code == null) ? new POTD(potd_title, potd_description, linkResources) :
                new POTD(potd_title, potd_description, linkResources, potd_code.toString());

        return potd_description;
    }
}

