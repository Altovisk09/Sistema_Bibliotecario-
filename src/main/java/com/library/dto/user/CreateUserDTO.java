package com.library.dto.user;

import com.library.model.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
    @NotBlank
    private String name;
    @NotNull
    private UserType userType;

    public CreateUserDTO(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
    }

    public CreateUserDTO(){

    }
}