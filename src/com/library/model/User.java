package com.library.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private String name;
    private UserType userType;
    private List<Loan> loanList;

    public User(int id, String name, UserType userType) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.loanList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }
}
