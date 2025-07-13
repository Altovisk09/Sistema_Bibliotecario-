package com.library.dto.user;

import com.library.model.User;
import com.library.model.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDTO {
    private String name;
    private UserType userType;

    public UpdateUserDTO(String name, UserType userType){
        this.name = name;
        this.userType = userType;
    }

    public UpdateUserDTO(){}
}
