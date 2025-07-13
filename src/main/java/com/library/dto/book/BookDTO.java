package com.library.dto.book;

import com.library.model.BookCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private String name;
    private String authorName;
    private BookCategory category;

    public BookDTO(String name, String authorName, BookCategory category){
        this.name = name;
        this.authorName = authorName;
        this.category = category;
    }

    public BookDTO(){}
}
