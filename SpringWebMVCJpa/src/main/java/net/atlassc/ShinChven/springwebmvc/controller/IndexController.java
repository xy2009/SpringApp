package net.atlassc.ShinChven.springwebmvc.controller;

import net.atlassc.ShinChven.springwebmvc.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShinChven on 15/3/28.
 */
@Controller
public class IndexController {


    /**
     * test controller
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello() {
        return new ModelAndView("hello");
    }


}
