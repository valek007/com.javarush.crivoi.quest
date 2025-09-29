package com.javarush.crivoi.quest.model.login;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private static final File FILE = new File("users.json");
    private static final ObjectMapper mapper = new ObjectMapper();
    private static List<User> users = loadUsers();

    private static List<User> loadUsers() {
        if (!FILE.exists()) return new ArrayList<>();
        try {
            return mapper.readValue(FILE, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void saveUsers() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(FILE, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean register(String username, String password) {
        if (findByUsername(username).isPresent()) return false;

        User newUser = new User(username, password, "1");
        users.add(newUser);
        saveUsers();
        return true;
    }

    public static User login(String username, String password) {
        Optional<User> userOpt = findByUsername(username);
        return userOpt.filter(user -> user.getPassword().equals(password)).orElse(null);
    }

    public static Optional<User> findByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    public static void updateProgress(String username, String nodeId) {
        findByUsername(username).ifPresent(user -> {
            user.setCurrentNodeId(nodeId);
            saveUsers();
        });
    }
}
