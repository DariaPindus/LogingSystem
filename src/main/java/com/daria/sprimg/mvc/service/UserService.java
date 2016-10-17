package com.daria.sprimg.mvc.service;

import com.daria.sprimg.mvc.model.User;
import com.daria.sprimg.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public boolean isRegistered(String name) {
        User user = userRepository.getUserByLogin(name);
        return userRepository.getUserByLogin(name) != null;
    }

    public boolean checkPassword(User found, String entered) {
        return found.getPassword().equals(entered);
    }

    public List<User> getUsersByCity(String city) {
        return userRepository.getUsersByCity(city);
    }
    public User getUserByLogin(String login){
        return userRepository.getUserByLogin(login);
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public void updateTokens(String newStr, String name){
        userRepository.updateValues(newStr, name);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
}
