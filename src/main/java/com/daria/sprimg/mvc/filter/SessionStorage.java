package com.daria.sprimg.mvc.filter;


import com.daria.sprimg.mvc.model.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionStorage {
    private static HashMap<HttpSession, User> userSessions = new HashMap<HttpSession, User>();
/*
    public SessionStorage(){
        userSessions = new HashMap<HttpSession, User>();
    }*/

    public HashMap<HttpSession, User> getUserSessions() {
        return userSessions;
    }

    public void setUserSessions(HashMap<HttpSession, User> userSessions) {
        this.userSessions = userSessions;
    }

    public static void addSession(HttpSession session, User user) {
        for (Map.Entry<HttpSession, User> entry : userSessions.entrySet()) {
            System.out.println("Show map after adding: ");
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        userSessions.put(session, user);
    }

    public static User getUserBySession(HttpSession session){
        return userSessions.get(session);
    }

    public static void removeSession(HttpSession session){
        userSessions.remove(session);
        for (Map.Entry<HttpSession, User> entry : userSessions.entrySet()) {
            System.out.println("Show map after removing: ");
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

}
