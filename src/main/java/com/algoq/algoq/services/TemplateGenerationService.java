package com.algoq.algoq.services;

import com.algoq.algoq.models.POTD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.io.IOException;

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

    /**
     * Handle posting to external API to grab syntax highlighted HTML from user input
     */
    public String syntaxHighlighting(String htmlInput) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://hilite.me/api";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("code", htmlInput);
        map.add("lexer", "java");
        map.add("style", "monokai");
        map.add("divstyles", "border:solid+gray;border-width:.1em+.1em+.1em+.8em;padding:.2em+.6em;");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
        log.info(response.getBody().toString());
        return response.getBody();
    }


}
