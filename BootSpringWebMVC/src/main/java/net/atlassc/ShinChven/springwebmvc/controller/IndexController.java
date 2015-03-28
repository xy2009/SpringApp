package net.atlassc.ShinChven.springwebmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ShinChven on 15/3/28.
 */
@Controller
public class IndexController {


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello() {
        System.out.println("hello===============");
        System.out.println("hello===============");
        System.out.println("hello===============");
        System.out.println("hello===============");
        System.out.println("hello===============");
        return new ModelAndView("hello");
    }
}
