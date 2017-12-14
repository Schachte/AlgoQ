package com.algoq.algoq.controller;

import com.algoq.algoq.models.POTD;
import com.algoq.algoq.services.MailService;
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

    private static final Logger log = LoggerFactory.getLogger(AlgorithmController.class);

    @RequestMapping(value = "/generatepotd", method = RequestMethod.POST)
    @ResponseBody
    public void generatePOTD(@RequestBody POTD problem) throws Exception {
        mailService.sendBulkEmail(problem);
    }

    @RequestMapping(value = "/highlight", method = RequestMethod.POST)
    @ResponseBody
    public String highlightTester(@RequestBody Map<String, String> programInput) throws UnsupportedEncodingException {
        PythonInterpreter interpreter = new PythonInterpreter();

        // Set a variable with the content you want to work with
        interpreter.set("code", getDecodedString(programInput));

        // Simple use Pygments as you would in Python
        interpreter.exec("from pygments import highlight\n"
                + "from pygments.lexers import PythonLexer\n"
                + "from pygments.formatters import HtmlFormatter\n"
                + "formatter = HtmlFormatter(style='monokai',"
                + "                            linenos=False,"
                + "                            noclasses=True,"
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

