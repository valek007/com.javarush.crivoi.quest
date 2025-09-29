package sands_of_fate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.javarush.crivoi.quest.model.login.User;
import com.javarush.crivoi.quest.model.login.UserRepository;

class UserRepositoryTest {

    @SuppressWarnings("unchecked")
	@BeforeEach
    void setup() throws Exception {
        // Limpiar la lista 'users' usando reflexión
        Field usersField = UserRepository.class.getDeclaredField("users");
        usersField.setAccessible(true);
        List<User> users = (List<User>) usersField.get(null);
        users.clear();

        // Borrar el archivo JSON real para que cada test empiece limpio
        Field fileField = UserRepository.class.getDeclaredField("FILE");
        fileField.setAccessible(true);
        File file = (File) fileField.get(null);
        if (file.exists()) file.delete();
    }

    @Test
    void testRegisterNewUser() {
        boolean result = UserRepository.register("Alice", "password123");
        assertTrue(result, "User should be registered successfully");

        User user = UserRepository.findByUsername("Alice").orElse(null);
        assertNotNull(user, "User should exist in repository");
        assertEquals("Alice", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("1", user.getCurrentNodeId());
    }

    @Test
    void testRegisterExistingUser() {
        UserRepository.register("Bob", "pass");
        boolean result = UserRepository.register("Bob", "newpass");
        assertFalse(result, "Registering existing user should fail");
    }

    @Test
    void testLoginSuccess() {
        UserRepository.register("Charlie", "mypassword");
        User user = UserRepository.login("Charlie", "mypassword");
        assertNotNull(user, "Login should succeed with correct credentials");
    }

    @Test
    void testLoginFailure() {
        UserRepository.register("Dave", "1234");
        User user = UserRepository.login("Dave", "wrongpassword");
        assertNull(user, "Login should fail with incorrect password");

        user = UserRepository.login("Nonexistent", "1234");
        assertNull(user, "Login should fail for non-existent user");
    }

    @Test
    void testUpdateProgress() {
        UserRepository.register("Eve", "pw");
        UserRepository.updateProgress("Eve", "5B");

        User user = UserRepository.findByUsername("Eve").orElse(null);
        assertEquals("5B", user.getCurrentNodeId(), "User progress should be updated");
    }
}