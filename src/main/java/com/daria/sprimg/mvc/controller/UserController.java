package com.daria.sprimg.mvc.controller;

import com.daria.sprimg.mvc.filter.AuthFilter;
import com.daria.sprimg.mvc.model.User;
import com.daria.sprimg.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.*;
import javax.validation.Valid;
import java.util.*;

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(HttpServletRequest request, @CookieValue(value = "tkn", defaultValue = "0") String cookieValue,
                               Model model) {

        HttpSession session = request.getSession();
        String userTknCookie = cookieValue;
        System.out.println("Got cookie value in login : " + cookieValue);
        User user = AuthFilter.getUserBySession(session);

        if (!cookieValue.equals("0") && user != null && userTknCookie.equals(user.getTkn())) {
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
        System.out.println("Entered post form");
        System.out.println("Got user in post login : " + user.getUserName());
        System.out.println("Got password : " + user.getPassword());
        if (userService.isRegistered(user.getUserName())) {
            User current = userService.getUserByLogin(user.getUserName());
            System.out.println("checked whether is registered");
            if (userService.checkPassword(current, user.getPassword())) {
                System.out.println("user Tkn after validating form: " + current.getTkn());
                Cookie cookie = new Cookie("tkn", current.getTkn());
                cookie.setMaxAge(2 * 60);
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
        List<String> countries = countries();
        model.addAttribute("countryList", countries);
        model.addAttribute("user", new User());
        return "Registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute(value = "user") User user, HttpServletRequest req, Model model) {
        String error = "";
        if (user.getUserName().length() < 3 || user.getUserName().length() > 20) {
            error = "Login must be from 3 to 20 symbols";
        } else if (user.getAddress().getCountry().length() ==0) {
            error = "Country is rewuired field";
        } else if (user.getAddress().getCity().length()==0) {
            error = "City is required field";
        } else if (userService.isRegistered(user.getUserName())) {
            error = "Login is already registered";
        }
        if (error.length()!=0){
            model.addAttribute("error", error);
            return "Registration";
        }
        else {
            user.setTkn(generateRandomString(60));
            userService.addUser(user);
            AuthFilter.addUser(req.getSession(), user);
            model.addAttribute("user", user);
            return "redirect:/login";
        }
    }


    @RequestMapping(value = "/service/success", method = RequestMethod.GET)
    public String success(@ModelAttribute("user") User user, @CookieValue(value = "tkn", defaultValue = "0000") String cookieValue,
                          HttpServletResponse res, Model model) {
        model.addAttribute("cookie", cookieValue);
        model.addAttribute("msg", user.getUserName());

        return "Success";
    }

    public void setNewTokens() {
        List<User> users = userService.getUsers();
        for (User user : users) {
            user.setTkn(generateRandomString(60));
            userService.updateUser(user);
        }
    }

    public List<String> countries() {
        List<String> countries = new ArrayList<String>();
        countries.add("Ukraine");
        countries.add("USA");
        countries.add("France");
        countries.add("Germany");
        return countries;
    }
    @RequestMapping(value = "/service/success", method = RequestMethod.POST)
    public String success(@RequestParam(value = "color") String color, HttpServletResponse response) {
        response.addCookie(new Cookie("color", color));
        return "redirect:/service/check";
    }

    @RequestMapping(value = "/service/check")
    public String check(HttpServletRequest request, @CookieValue(value = "color") String color,
                        @CookieValue(value = "userName") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("color", color);
        return "New";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String viewAllUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("userList", users);
        return "ViewUsers";
    }

    @RequestMapping(value = "/users/showByCity", method = RequestMethod.POST)
    public String viewUserByCity(@RequestParam("city") String city, Model model) {
        System.out.println("read city = " + city);
        List<User> users = userService.getUsersByCity(city);
        System.out.println("Show users list");

        model.addAttribute("userList", users);
        return "UsersByCity";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response, SessionStatus status) {
        System.out.println("in /logout");
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            //c.setPath("/");
            c.setMaxAge(0);
            response.addCookie(c);
        }
        session.invalidate();
        status.setComplete();
        return "redirect:/login";
    }
    
    public String generateRandomString(int length) {
        String chars = "abcdefghijklmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt((int) (Math.random() * chars.length())));
        }
        return sb.toString();
    }
}
