package com.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
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

    public Loan(User user, Book book, LocalDate startDate, LocalDate finalDate, LoanStatus status){
        this.user = user;
        this.book = book;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.status = status;
    }

    public Loan(){}
}
