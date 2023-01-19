package kr.jenna.plmography.exceptions;

public class SignupFailed extends RuntimeException {
    public SignupFailed() {
    }

    public SignupFailed(String value) {
        super(value);
    }
}
