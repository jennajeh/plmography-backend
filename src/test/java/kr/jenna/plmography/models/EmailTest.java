package kr.jenna.plmography.models;

import kr.jenna.plmography.exceptions.InvalidEmail;
import kr.jenna.plmography.models.vo.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {

    @Test
    void creation() {
        assertDoesNotThrow(
                () -> new Email("test@email.com")
        );
    }

    @Test
    void withInvalidEmail() {
        assertThrows(InvalidEmail.class, () -> {
            new Email("incorrectEmail");
        });
    }

}
