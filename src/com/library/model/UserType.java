package com.library.model;

public enum UserType {
    STUDENT_GRADUATION(14),
    STUDENT_POSTGRADUATION(21),
    TEACHER(28);

    private final int borrowedDays;

    UserType(int borrowedDays){
        this.borrowedDays = borrowedDays;
    }

    public int getBorrowedDays(){
        return borrowedDays;
    }
}



