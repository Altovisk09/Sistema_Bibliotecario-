package com.library.dto.book;

import com.library.model.Book;
import com.library.model.BookCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private Long id;
    private String name;
    private String authorName;
    private BookCategory category;

    public BookDTO(Book book){
        this.id = book.getId();
        this.name = book.getName();
        this.authorName = book.getAuthorName();
        this.category = book.getCategory();
    }

    public BookDTO(){}
}
