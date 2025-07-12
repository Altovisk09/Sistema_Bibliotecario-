package com.library.model;

public enum UserType {
    STUDENT_GRADUATION(14, 3, "Graduação"),
    STUDENT_POSTGRADUATION(21, 3, "Pós-graduação"),
    TEACHER(28, 5, "Professor");

    private final int borrowedDays;
    private final int loanLimit;
    private final String displayName;

    UserType(int borrowedDays, int loanLimit, String displayName){
        this.borrowedDays = borrowedDays;
        this.loanLimit = loanLimit;
        this.displayName = displayName;
    }

    public int getBorrowedDays(){
        return borrowedDays;
    }

    public int getLoanLimit() {return loanLimit;}

    public String getDisplayName(){return displayName;}

    public String toString(){
        return String.format("%s - Prazo de empréstimo: %d, Limite de livros simultaneos: %d",
                name(),borrowedDays,loanLimit);
    }
}


