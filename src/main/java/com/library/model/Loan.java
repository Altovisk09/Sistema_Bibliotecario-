package com.library.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "loan_table")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Book book;

    @ManyToOne(optional = false)
    private User user;

    private LocalDate startDate;
    private LocalDate finalDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    public Long getId() {
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

    @Override
    public String toString() {
        return String.format(
                "Empréstimo ID: %d%n" +
                        "- Usuário: %s (ID %d)%n" +
                        "- Livro: \"%s\" (ID %d)%n" +
                        "- Início: %s | Devolução prevista: %s%n" +
                        "- Status: %s%n",
                id,
                user.getName(), user.getId(),
                book.getName(), book.getId(),
                startDate, finalDate,
                status
        );
    }
    public Loan(){

    }

    public Loan(User user, Book book, LocalDate inicialDate, LocalDate finalDate, LoanStatus status){
        this.user = user;
        this.book = book;
        this.startDate = inicialDate;
        this.finalDate = finalDate;
        this.status = status;
    }

}
