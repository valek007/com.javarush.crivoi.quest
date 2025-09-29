package sands_of_fate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.javarush.crivoi.quest.model.login.User;
import com.javarush.crivoi.quest.model.login.UserRepository;

class UserRepositoryTest {

    @BeforeEach
    void setup() {
        // Clear repository before each test to ensure independence
        // Since UserRepository uses a static Map, we need to reset it
        // Reflection is needed because the 'users' map is private
        try {
            var field = UserRepository.class.getDeclaredField("users");
            field.setAccessible(true);
            ((java.util.Map<?, ?>) field.get(null)).clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRegisterNewUser() {
        boolean result = UserRepository.register("Alice", "password123");
        assertTrue(result, "User should be registered successfully");
        
        User user = UserRepository.findByUsername("Alice");
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
        
        User user = UserRepository.findByUsername("Eve");
        assertEquals("5B", user.getCurrentNodeId(), "User progress should be updated");
    }
}
