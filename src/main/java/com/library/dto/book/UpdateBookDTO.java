package com.library.dto.book;

import com.library.model.BookCategory;
import jakarta.validation.constraints.Min;
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
    @Min(value = 1, message = "O número de cópias deve ser pelo menos 1.")
    private Integer totalCopies;

    public UpdateBookDTO(String name, String authorName, BookCategory category, Integer totalCopies){
        this.name = name;
        this.authorName = authorName;
        this.category = category;
        this.totalCopies = totalCopies;
    }

    public UpdateBookDTO(){}
}
