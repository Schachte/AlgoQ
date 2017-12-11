package com.algoq.algoq.controller;

import com.algoq.algoq.Constants.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class TemplateController {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(Model model) throws IOException {
        model.addAttribute("name", "Ryan Schachte");
        Context context = new Context();

        //TODO: Need to abstract this into a different function to set all the context variables
        context.setVariable("name", "Ryan Schachte");

        context = setCtxVariables(context);

        //Process the template with the proper context variables
        String html = templateEngine.process("email", context);
        PrintWriter pWriter = new PrintWriter(Paths.FILE_RESOURCES + "tester.html", "UTF-8");
        pWriter.println(html);
        pWriter.close();

        return html;
    }

    /**
     * Sets the context variables for the rendered template
     * @return
     */
    public Context setCtxVariables(Context ctx) {

        return ctx;
    }
}

