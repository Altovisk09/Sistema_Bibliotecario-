package com.library.dto.book;

import com.library.model.BookCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookDTO {
    @NotBlank(message = "O nome do livro é obrigatório.")
    private String name;

    @NotBlank(message = "O nome do autor é obrigatório.")
    private String authorName;

    @NotNull(message = "A categoria é obrigatória.")
    private BookCategory category;

    @Min(value = 1, message = "O número de cópias deve ser pelo menos 1.")
    private int totalCopies;

    public CreateBookDTO(String name, String authorName, BookCategory category, Integer totalCopies){
        this.name = name;
        this.authorName = authorName;
        this.category = category;
        this.totalCopies = totalCopies;
    }

    public CreateBookDTO(){}
}
