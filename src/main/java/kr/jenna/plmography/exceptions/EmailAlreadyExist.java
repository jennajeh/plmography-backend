package kr.jenna.plmography.exceptions;

import kr.jenna.plmography.models.VO.Email;

public class EmailAlreadyExist extends SignupFailed {
    public EmailAlreadyExist(Email email) {
        super(email.getValue());
    }
}
