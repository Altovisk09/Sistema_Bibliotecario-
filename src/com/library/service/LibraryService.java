package com.library.service;

import com.library.exceptions.BookUnavailableException;
import com.library.model.*;
import com.library.util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private List<Loan> loanList;
    private List<User> usersList;
    private List<Book> booksList;

    public LibraryService(){
        this.usersList = new ArrayList<>();
        this.booksList = new ArrayList<>();
        this.loanList = new ArrayList<>();
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    public List<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Book> booksList) {
        this.booksList = booksList;
    }

    public void createUser(String name, UserType userType){
        int newId = IdGenerator.getNextId(usersList, User::getId);
        User newUser = new User(newId, name, userType);
        usersList.add(newUser);
    }
    public void RegisterBook(String name, String autorName, BookCategory category, int totalCopies){
        int newId = IdGenerator.getNextId(booksList, Book::getId);
        Book newBook = new Book(newId, name, autorName, category, totalCopies);
        booksList.add(newBook);
    }

    public void RegisterLoan(User user, Book book){
        try{
            int newId = IdGenerator.getNextId(loanList,Loan::getId);
            int borrowedDays = user.getUserType().getBorrowedDays();
            LocalDate startDate = LocalDate.now();
            LocalDate finalDate = startDate.plusDays(borrowedDays);
            LoanStatus status = LoanStatus.ACTIVE;
            Loan newLoan = new Loan(newId, user, book, startDate, finalDate, status);
            loanList.add(newLoan);
            book.lendBook();
        } catch (BookUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }
}



