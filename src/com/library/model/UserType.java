package com.library.model;

public enum UserType {
    STUDENT_GRADUATION(14, 3),
    STUDENT_POSTGRADUATION(21, 3),
    TEACHER(28, 5);

    private final int borrowedDays;
    private final int loanLimit;

    UserType(int borrowedDays, int loanLimit){
        this.borrowedDays = borrowedDays;
        this.loanLimit = loanLimit;
    }

    public int getBorrowedDays(){
        return borrowedDays;
    }

    public int getLoanLimit() {return loanLimit;}
}


