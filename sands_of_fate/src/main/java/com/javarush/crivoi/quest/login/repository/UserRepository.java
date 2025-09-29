package com.javarush.crivoi.quest.login.repository;

import java.util.HashMap;
import java.util.Map;

import com.javarush.crivoi.quest.login.model.User;

public class UserRepository {
    private static final Map<String, User> users = new HashMap<>();

    public static boolean register(String username, String password) {
        if (users.containsKey(username)) return false;
        users.put(username, new User(username, password, "1"));
        return true;
    }

    public static User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public static User findByUsername(String username) {
        return users.get(username);
    }

    public static void updateProgress(String username, String nodeId) {
        User user = users.get(username);
        if (user != null) {
            user.setCurrentNodeId(nodeId);
        }
    }
}
