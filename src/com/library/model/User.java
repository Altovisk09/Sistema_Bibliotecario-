package com.library.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private String name;
    private UserType userType;

    public User(int id, String name, UserType userType) {
        this.id = id;
        this.name = name;
        this.userType = userType;
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

    public String toString(){
        return String.format("User:{Id: %d, Name:%s, Type: %s}", id, name, userType);
    }
}
