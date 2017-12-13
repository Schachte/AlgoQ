package com.algoq.algoq.services;

import com.algoq.algoq.Constants.Paths;
import com.algoq.algoq.models.POTD;
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
    public String generateProblemOfTheDay(POTD problem) throws IOException {

        log.info("This is passed");
        Context ctx = new Context();

        //TODO: Add in the setCtx function
        ctx.setVariable("potd_title", problem.getProblemTitle());
        ctx.setVariable("potd_resources", problem.getResources());
        ctx.setVariable("potd_problem", problem.getProblemDescription());

        //Process the template with the proper context variables
        String html = templateEngine.process("index", ctx);
//        PrintWriter pWriter = new PrintWriter(Paths.PROBLEM_OF_THE_DAY_OUTPUT, "UTF-8");
//
//        pWriter.println(html);
//        pWriter.close();
        log.info("done!");

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
