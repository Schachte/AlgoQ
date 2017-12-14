package com.algoq.algoq.controller;

import com.algoq.algoq.models.POTD;
import com.algoq.algoq.services.MailService;
import com.algoq.algoq.services.TemplateGenerationService;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

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

}

