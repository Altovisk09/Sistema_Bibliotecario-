package com.library.model;

import java.time.LocalDate;

public class Loan {
    private final int id;
    private final Book book;
    private final User user;
    private LocalDate startDate;
    private LocalDate finalDate;
    private LoanStatus status;

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public Loan(int id, User user, Book book, LocalDate inicialDate, LocalDate finalDate, LoanStatus status){
        this.id = id;
        this.user = user;
        this.book = book;
        this.startDate = inicialDate;
        this.finalDate = finalDate;
        this.status = status;
    }

}
