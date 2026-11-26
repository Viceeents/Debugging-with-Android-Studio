package com.example.appdev_debugging.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void validateEmail_acceptsTwoLetterTLD() {
        assertNull(Validator.validateEmail("juan@school.ph"));
        assertNull(Validator.validateEmail("juan@up.edu.ph"));
        assertNull(Validator.validateEmail("user@example.com"));
    }

    @Test
    public void validateEmail_rejectsInvalidEmails() {
        assertNotNull(Validator.validateEmail("invalid-email"));
        assertNotNull(Validator.validateEmail("user@domain"));
    }

    @Test
    public void validateNewPassword_requiresLettersAndNumbers() {
        assertNotNull(Validator.validateNewPassword("abcdefgh")); // missing digits
        assertNotNull(Validator.validateNewPassword("12345678")); // missing letters
        assertNull(Validator.validateNewPassword("abcd1234"));     // valid
        assertNull(Validator.validateNewPassword("Password123")); // valid
    }
}
