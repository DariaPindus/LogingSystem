package com.daria.sprimg.mvc.controller;

import com.daria.sprimg.mvc.filter.AuthFilter;
import com.daria.sprimg.mvc.model.User;
import com.daria.sprimg.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.*;
import java.util.*;

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(HttpServletRequest request, @CookieValue(value = "tkn", defaultValue = "0")String cookieValue,
                               Model model){
        HttpSession session = request.getSession();
        String userTknCookie = cookieValue;
        System.out.println("Got cookie value in login : " + cookieValue);
        User user = AuthFilter.getUserBySession(session);

        if (!cookieValue.equals("0") && user!=null && userTknCookie.equals(user.getTkn())) {
            //User user = AuthFilter.getUserBySession(session);
            session.setAttribute("user", user);
            model.addAttribute("user", user);
            return "redirect:/service/success";
        }

        model.addAttribute("user", new User());
        return "Login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String enter(@ModelAttribute("user") User user,
                        HttpServletRequest req, HttpServletResponse res, Model model) {

        if (userService.isRegistered(user.getUserName())) {
            User current = userService.getUserByLogin(user.getUserName());
            //System.out.println("returned user password = " + current.getPassword());
            if (userService.checkPassword(current, user.getPassword())) {
                System.out.println("user Tkn after validating form: " + current.getTkn());
                Cookie cookie = new Cookie("tkn", current.getTkn());
                cookie.setMaxAge(2*60);
                res.addCookie(cookie);
                AuthFilter.addUser(req.getSession(), current);
                model.addAttribute("msg", user.getUserName());
                return "redirect:/service/success";
            }
        }
        model.addAttribute("error", "Wrong input");
        return "Login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "Registration";
    }

    @RequestMapping(value = "/service/success", method = RequestMethod.GET)
    public String success(@ModelAttribute("user") User user, @CookieValue (value = "tkn", defaultValue = "0000") String cookieValue,
                          HttpServletResponse res, Model model) {
        //model.addAttribute("user", user);
/*
        ArrayList<String> colors = getColors();
        model.addAttribute("colors", colors);*/
        model.addAttribute("cookie", cookieValue);
        model.addAttribute("msg", user.getUserName());

        return "Success";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@RequestParam(value = "userName") String userName,
                           @RequestParam(value = "password") String password, HttpServletRequest req, Model model) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(User.getShaHash(password));
        user.setTkn(setRandomString());
        if (userService.isRegistered(user.getUserName())) {
            model.addAttribute("error", "Login is already registered");
            return "Registration";
        } else {
            userService.addUser(user);
            AuthFilter.addUser(req.getSession(),user);
            model.addAttribute("user", user);
            return "redirect:/service/success";
        }
    }

    protected ArrayList<String> getColors(){
        ArrayList<String> colors = new ArrayList<String>();
        colors.add("white");
        colors.add("green");
        colors.add("pink");
        colors.add("blue");
        return colors;
    }

    @RequestMapping(value = "/service/success", method = RequestMethod.POST)
    public String success(@RequestParam(value = "color") String color, HttpServletResponse response){
        response.addCookie(new Cookie("color", color));
        return "redirect:/service/check";
    }

    @RequestMapping(value = "/service/check")
    public String check(HttpServletRequest request, @CookieValue(value = "color")String color,
                        @CookieValue(value = "userName") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("color", color);
        return "New";
    }


    @RequestMapping(value = "/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response, SessionStatus status) {
        System.out.println("in /logout");
        Cookie[] cookies = request.getCookies();
        for (Cookie c:cookies) {
            //c.setPath("/");
            c.setMaxAge(0);
            response.addCookie(c);
        }
        session.invalidate();
        status.setComplete();
        return "redirect:/login";
    }

    public String setRandomString(){
        Random r = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 60; i++) {
            int p = 32 + r.nextInt(90);
            char c = (char) p;
            str.append(c);
        }
        return str.toString();
    }
}
