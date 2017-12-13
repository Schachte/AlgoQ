package com.algoq.algoq.controller;

import com.algoq.algoq.services.TemplateGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

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
    public void problemGenerator(Model model) throws IOException {
        tempGen.generateProblemOfTheDay(model);
    }
}

