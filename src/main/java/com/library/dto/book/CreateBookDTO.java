package com.library.dto.book;

import com.library.model.BookCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String authorName;
    @NotNull
    private BookCategory category;

    public CreateBookDTO(String name, String authorName, BookCategory category){
        this.name = name;
        this.authorName = authorName;
        this.category = category;
    }

    public CreateBookDTO(){}
}
