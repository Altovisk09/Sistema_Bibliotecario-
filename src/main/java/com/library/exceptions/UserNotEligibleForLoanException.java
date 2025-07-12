package com.library.exceptions;

public class UserNotEligibleForLoanException extends Exception {
    public UserNotEligibleForLoanException(String message) {
        super(message);
    }
}
