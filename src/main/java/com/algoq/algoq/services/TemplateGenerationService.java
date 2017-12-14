package com.algoq.algoq.services;

import com.algoq.algoq.models.POTD;
import org.python.util.PythonInterpreter;
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
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Service
public class TemplateGenerationService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    private static final Logger log = LoggerFactory.getLogger(TemplateGenerationService.class);

    /**
     * Generate the template for the problem of the day
     */
    public String generateProblemOfTheDay(POTD problem) throws IOException {

        Context ctx = new Context();
        setCtxVariables(ctx, problem);
        String html = templateEngine.process("index", ctx);
        log.info("done!");
        return html;
    }

    /**
     * Sets the context variables for the rendered template
     * @return
     */
    public Context setCtxVariables(Context ctx, POTD problem) {
        ctx.setVariable("potd_title", problem.getProblemTitle());
        ctx.setVariable("potd_resources", problem.getResources());
        ctx.setVariable("potd_problem", problem.getProblemDescription());
        return ctx;
    }

    /**
     * Handle posting to external API to grab syntax highlighted HTML from user input
     */
    public String syntaxHighlighting(Map<String, String> programInput) {
        PythonInterpreter interpreter = new PythonInterpreter();

        // Set a variable with the content you want to work with
        interpreter.set("code", getDecodedString(programInput));

        // Simple use Pygments as you would in Python
        interpreter.exec("from pygments import highlight\n"
                + "from pygments.lexers import PythonLexer\n"
                + "from pygments.formatters import HtmlFormatter\n"
                + "formatter = HtmlFormatter(style='monokai',"
                + "                            linenos=true,"
                + "                            noclasses=true,"
                + "                            cssclass='',"
                + "                            prestyles='margin: 0')"
                + "\nresult = highlight(code, PythonLexer(), formatter)");

        // Get the result that has been set in a variable
        return interpreter.get("result", String.class);
    }

    /**
     * Parse out base64 encoded string to highlight
     * @param base64EncodedCode
     * @return
     */
    public String getDecodedString(Map<String, String> base64EncodedCode) {
        byte[] decoded = Base64.getDecoder().decode(base64EncodedCode.get("programInput"));
        return new String(decoded, StandardCharsets.UTF_8);
    }
}
