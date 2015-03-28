package net.atlassc.ShinChven.springwebmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ShinChven on 15/3/28.
 */
@Controller
public class QuestionController {

    @RequestMapping(value = "/question_list", method = RequestMethod.GET)
    public String questionList() {

        return "question_list";
    }

    @RequestMapping(value = "/question_input", method = RequestMethod.POST)
    public void inputQuestion(HttpServletRequest request, HttpServletResponse response, String title, String answers) {
        System.out.println(title);
        System.out.println(answers);
        try {
            response.sendRedirect("/question_list.do");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
