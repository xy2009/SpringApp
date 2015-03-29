package net.atlassc.ShinChven.springwebmvc.controller;

import net.atlassc.ShinChven.springwebmvc.entity.UserEntity;
import net.atlassc.ShinChven.springwebmvc.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ShinChven on 15/3/29.
 */
@Controller
public class UserController {


    @RequestMapping(value = "register_user", method = RequestMethod.POST)
    public void registerUser(String username, String password, int age) {
        UserEntity user = new UserEntity();
        user.setUserName(username);
        user.setAge(age);
        user.setPassword(password);
        userRepo.save(user);
        System.out.println(user.getUid());
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @Autowired
    private UserRepo userRepo;

    /**
     * test json responseBody
     *
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public
    @ResponseBody
    List<UserEntity> getUsers() {

        return userRepo.findAll();
    }
}
