package kr.jenna.plmography.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound(Long userId) {
        super("User not found : " + userId);
    }

    public UserNotFound() {
        super("User not found");
    }
}
