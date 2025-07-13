package com.library.dto.user;

import com.library.model.User;
import com.library.model.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private UserType userType;

    public UserDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.userType = user.getUserType();
    }
}
