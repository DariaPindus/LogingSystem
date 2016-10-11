package com.daria.sprimg.mvc.filter;


import com.daria.sprimg.mvc.model.User;
import com.daria.sprimg.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = "/jpa/service/*")
public class AuthFilter implements Filter {

    static HashMap<HttpSession, User> usersSessions = new HashMap<HttpSession, User>();

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter was initialized");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession currentSession = request.getSession();
    //    if (usersSessions.get(currentSession) != null) {
        //User user = SessionStorage.getUserBySession(currentSession);
        User user = usersSessions.get(currentSession);
        if (user!=null) {
            System.out.println("in checking");
            currentSession.setAttribute("user", user);
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    public void destroy() {

    }

    public static void addUser(HttpSession session, User user) {
        System.out.println("inside auth filter: " + session + "\n user: " + user.getName());
        usersSessions.put(session, user);
        for (Map.Entry<HttpSession, User> entry : usersSessions.entrySet()) {
            System.out.println("Show map after adding: ");
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        //SessionStorage.addSession(session, user);
    }

    public static void removeUser(HttpSession session) {
        System.out.println("in authFilter deleting ");
        usersSessions.remove(session);
        //SessionStorage.removeSession(session);
        System.out.println("Show map after removing: ");
        for (Map.Entry<HttpSession, User> entry : usersSessions.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

    public static boolean isUserInSession(HttpSession session) {
        return usersSessions.containsKey(session);
    }

    public static User getUserBySession(HttpSession session) {
        return usersSessions.get(session);
    }

}
