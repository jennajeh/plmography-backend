package kr.jenna.plmography.models;

import kr.jenna.plmography.exceptions.InvalidPassword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordTest {

    @Test
    void creation() {
        assertDoesNotThrow(() -> {
            new Password("Abcdef1!");
        });
    }

    @Test
    void shortPassword() {
        assertThrows(InvalidPassword.class, () -> {
            new Password("Abcde1!");
        });
    }

    @Test
    void passwordWithoutLowerCase() {
        assertThrows(InvalidPassword.class, () -> {
            new Password("ABCDEF1!");
        });
    }

    @Test
    void passwordWithoutUpperCase() {
        assertThrows(InvalidPassword.class, () -> {
            new Password("abcdef1!");
        });
    }

    @Test
    void passwordWithoutNumber() {
        assertThrows(InvalidPassword.class, () -> {
            new Password("Abcdefg!");
        });
    }

    @Test
    void passwordWithoutSpecialCharacter() {
        assertThrows(InvalidPassword.class, () -> {
            new Password("Abcdefg1");
        });
    }
}
