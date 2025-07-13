package com.library.dto.book;

import com.library.model.BookCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookDTO {
    private String name;
    private String authorName;
    private BookCategory category;

    public UpdateBookDTO(String name, String authorName, BookCategory category){
        this.name = name;
        this.authorName = authorName;
        this.category = category;
    }

    public UpdateBookDTO(){}
}
