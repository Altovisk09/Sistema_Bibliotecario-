package com.library.exceptions;

public class UserLoanLimitException extends Exception {
    public UserLoanLimitException(String message) {
        super(message);
    }
}
