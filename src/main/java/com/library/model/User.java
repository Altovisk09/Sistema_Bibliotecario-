package com.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
    }
    public User(){}

    public String toString(){
        return String.format("User:{Id: %d, Name:%s, Type: %s}", id, name, userType);
    }

}
