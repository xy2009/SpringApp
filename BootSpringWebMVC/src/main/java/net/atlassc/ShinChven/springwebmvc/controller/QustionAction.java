package net.atlassc.ShinChven.springwebmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ShinChven on 15/3/28.
 */
public class QustionAction {



    public void inputQuestion(){

    }

    @RequestMapping(name = "question_list", method = RequestMethod.GET)
    public String questionList(){

        return "question_list";
    }

}
