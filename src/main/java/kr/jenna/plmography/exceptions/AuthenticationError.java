package kr.jenna.plmography.exceptions;

public class AuthenticationError extends RuntimeException {
    public AuthenticationError() {
        super("Authentication error");
    }
}
