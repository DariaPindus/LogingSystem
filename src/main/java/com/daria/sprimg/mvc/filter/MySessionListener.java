package com.daria.sprimg.mvc.filter;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MySessionListener implements HttpSessionListener{
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("Session delets");
        AuthFilter.removeUser(httpSessionEvent.getSession());
        //SessionStorage.removeSession(httpSessionEvent.getSession());
    }
}
