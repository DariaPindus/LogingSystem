package com.daria.sprimg.mvc.controller;

import com.daria.sprimg.mvc.filter.AuthFilter;
import com.daria.sprimg.mvc.model.User;
import com.daria.sprimg.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    UserService userService;

/*
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginForm() {

        ModelAndView model = new ModelAndView("Login");
        model.addObject("user", new User());
        return model;
    }
*/

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        if (AuthFilter.getUserBySession(session) != null) {
            User user = AuthFilter.getUserBySession(session);
            model.addAttribute("user", user);
            session.setAttribute("user", user);
            return "redirect:/service/success";
        }
        model.addAttribute("user", new User());
        return "Login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String enter(@ModelAttribute("user") User user, HttpServletRequest req, Model model) {

        if (userService.isRegistered(user.getName())) {
            User current = userService.getUserByLogin(user.getName());
            System.out.println("returned user password = " + current.getPassword());
            if (userService.checkPassword(current, user.getPassword())) {
                System.out.println("inside second check:" + current.getName());
                System.out.println("session: " + req.getSession());
                AuthFilter.addUser(req.getSession(), current);
                req.getSession().setAttribute("user", user);
                model.addAttribute("msg", user.getName());
                //attr.addFlashAttribute("msg", user.getName());
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
    public String success(@ModelAttribute("user") User user, Model model) {
        //model.addAttribute("user", user);
        model.addAttribute("msg", user.getName());
        return "Success";
    }

    @RequestMapping(value = "/service/check")
    public String check() {
        return "New";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@RequestParam(value = "userName") String userName,
                           @RequestParam(value = "password") String password, HttpServletRequest req, Model model) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(User.getShaHash(password));
        if (userService.isRegistered(user.getName())) {
            model.addAttribute("error", "Login is already registered");
            return "Registration";
        } else {
            userService.addUser(user);
            AuthFilter.addUser(req.getSession(),user);
            model.addAttribute("user", user);
            return "redirect:/service/success";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        System.out.println("in /logout");
        session.invalidate();
        //status.setComplete();
        return "redirect:/login";
    }
}
