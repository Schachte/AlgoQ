package com.algoq.algoq.services;

import com.algoq.algoq.Constants.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Service
public class TemplateGenerationService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    private static final Logger log = LoggerFactory.getLogger(TemplateGenerationService.class);

    /**
     * Generate the template for the problem of the day
     */
    public String generateProblemOfTheDay(Model model) throws IOException {

        log.info("This is passed");
//        model.addAttribute("potd_question", "Ryan Schachte");
//        model.addAttribute("potd_title", "sup");
//
//        ArrayList<String> list = new ArrayList<>();
//        list.add("A");
//        list.add("B");
//        model.addAttribute("potd_resources", list);

//        Context ctx = setCtxVariables(new Context());
        Context ctx = new Context();
        ctx.setVariable("potd", "Ryan Schachte");

        //Process the template with the proper context variables
        String html = templateEngine.process("index", ctx);
        PrintWriter pWriter = new PrintWriter(Paths.PROBLEM_OF_THE_DAY_OUTPUT, "UTF-8");
        pWriter.println(html);
        pWriter.close();
        log.info("done!");
        log.info(html);

        return html;
    }

    /**
     * Sets the context variables for the rendered template
     * @return
     */
    public Context setCtxVariables(Context ctx) {
        ctx.setVariable("potd_question", "Ryan Schachte");

        return ctx;
    }


}
