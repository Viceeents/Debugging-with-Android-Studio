package com.example.appdev_debugging.auth;

import com.example.appdev_debugging.data.InMemoryUserRepository;
import com.example.appdev_debugging.data.UserRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthManagerTest {

    private AuthManager authManager;

    @Before
    public void setUp() {
        UserRepository repository = new InMemoryUserRepository();
        authManager = new AuthManager(repository);
    }

    @Test
    public void login_withUppercasePassword_succeeds() {
        AuthResult regResult = authManager.register("Test User", "test@example.com", "Password123", "Password123");
        assertTrue(regResult.isSuccess());

        AuthResult loginResult = authManager.login("test@example.com", "Password123");
        assertTrue(loginResult.isSuccess());
    }

    @Test
    public void login_withCaseInsensitiveEmail_succeeds() {
        AuthResult regResult = authManager.register("Juan Dela Cruz", "Juan.DelaCruz@Mail.com", "Password123", "Password123");
        assertTrue(regResult.isSuccess());

        AuthResult loginResultLower = authManager.login("juan.delacruz@mail.com", "Password123");
        assertTrue(loginResultLower.isSuccess());

        AuthResult loginResultMixed = authManager.login("Juan.DelaCruz@Mail.com", "Password123");
        assertTrue(loginResultMixed.isSuccess());
    }

    @Test
    public void register_duplicateEmailDifferentCase_fails() {
        AuthResult regResult1 = authManager.register("User One", "Juan.DelaCruz@Mail.com", "Password123", "Password123");
        assertTrue(regResult1.isSuccess());

        AuthResult regResult2 = authManager.register("User Two", "juan.delacruz@mail.com", "Password123", "Password123");
        assertFalse(regResult2.isSuccess());
        assertEquals(AuthResult.Field.EMAIL, regResult2.getField());
    }
}
