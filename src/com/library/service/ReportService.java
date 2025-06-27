package com.library.service;

import com.library.model.Book;
import com.library.model.Loan;
import com.library.model.LoanStatus;
import com.library.model.User;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ReportService {

    private final LibraryService libraryService;

    public ReportService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void listUsersWithActiveLoans() {
        System.out.println("📋 Usuários com empréstimos ativos ou atrasados:");
        List<User> users = libraryService.getUsersList();
        boolean found = false;

        for (User user : users) {
            List<Loan> activeLoans = libraryService.getLoanList().stream().filter(loan -> loan.getUser().equals(user)).filter(loan -> loan.getStatus() == LoanStatus.ACTIVE || loan.getStatus() == LoanStatus.LATE).toList();

            if (!activeLoans.isEmpty()) {
                found = true;
                System.out.println("👤 " + user.getName() + ":");
                for (Loan loan : activeLoans) {
                    System.out.printf("  📘 Livro: %s | Devolução: %s | Status: %s%n", loan.getBook().getName(), loan.getFinalDate(), loan.getFinalDate().isBefore(LocalDate.now()) ? "🔴 Atrasado" : "🟢 Ativo");
                }
            }
        }

        if (!found) {
            System.out.println("Nenhum usuário possui empréstimos ativos no momento.");
        }
    }

    public void listMostBorrowedBooks() {
        System.out.println("📊 Top 5 livros mais emprestados:");

        List<Loan> loans = libraryService.getLoanList();

        Map<Book, Long> countMap = loans.stream().collect(Collectors.groupingBy(Loan::getBook, Collectors.counting()));

        List<Map.Entry<Book, Long>> topBooks = countMap.entrySet().stream().sorted((a, b) -> Long.compare(b.getValue(), a.getValue())).limit(5).toList();

        if (topBooks.isEmpty()) {
            System.out.println("Ainda não há registros de empréstimos.");
            return;
        }

        int rank = 1;
        for (Map.Entry<Book, Long> entry : topBooks) {
            System.out.printf("%dº - \"%s\" (%d empréstimos)%n", rank++, entry.getKey().getName(), entry.getValue());
        }
    }

    public void listOverdueLoans() {
        System.out.println("🚨 Empréstimos atrasados:");

        List<Loan> overdueLoans = libraryService.getLoanList().stream().filter(loan -> (loan.getStatus() == LoanStatus.ACTIVE || loan.getStatus() == LoanStatus.LATE)).filter(loan -> loan.getFinalDate().isBefore(LocalDate.now())).toList();

        if (overdueLoans.isEmpty()) {
            System.out.println("Nenhum empréstimo atrasado encontrado.");
            return;
        }

        for (Loan loan : overdueLoans) {
            System.out.printf("ID: %d | Usuário: %s | Livro: %s | Vencimento: %s%n", loan.getId(), loan.getUser().getName(), loan.getBook().getName(), loan.getFinalDate());
        }
    }

    public void printStatistics() {
        long totalUsers = libraryService.getUsersList().size();
        long totalBooks = libraryService.getBooksList().size();
        long totalLoans = libraryService.getLoanList().size();
        long activeLoans = libraryService.getLoanList().stream().filter(loan -> loan.getStatus() == LoanStatus.ACTIVE || loan.getStatus() == LoanStatus.LATE).count();

        System.out.println("📈 Estatísticas do Sistema:");
        System.out.printf("👤 Total de usuários: %d%n", totalUsers);
        System.out.printf("📚 Total de livros: %d%n", totalBooks);
        System.out.printf("🔄 Empréstimos registrados: %d%n", totalLoans);
        System.out.printf("📌 Empréstimos ativos ou atrasados: %d%n", activeLoans);
    }
}
