package kr.jenna.plmography.models;

import kr.jenna.plmography.exceptions.InvalidPassword;
import kr.jenna.plmography.models.VO.Password;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordTest {

    @Test
    void creation() {
        assertDoesNotThrow(() -> {
            new Password("Test123!");
        });
    }

    @Test
    void shortPassword() {
        assertThrows(InvalidPassword.class, () -> {
            new Password("Test1!");
        });
    }

    @Test
    void passwordWithoutLowerCase() {
        assertThrows(InvalidPassword.class, () -> {
            new Password("TEST123!");
        });
    }

    @Test
    void passwordWithoutUpperCase() {
        assertThrows(InvalidPassword.class, () -> {
            new Password("test123!");
        });
    }

    @Test
    void passwordWithoutNumber() {
        assertThrows(InvalidPassword.class, () -> {
            new Password("Testxxx!");
        });
    }

    @Test
    void passwordWithoutSpecialCharacter() {
        assertThrows(InvalidPassword.class, () -> {
            new Password("Testxxx1");
        });
    }
}
