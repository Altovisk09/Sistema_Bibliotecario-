package com.library.dto.loan;

import com.library.dto.book.BookDTO;
import com.library.dto.user.UserDTO;
import com.library.model.Loan;
import com.library.model.LoanStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LoanDTO {
    private Long id;
    private BookDTO book;
    private UserDTO user;
    private LocalDate startDate;
    private LocalDate finalDate;
    private LoanStatus status;

    public LoanDTO(Loan loan){
        this.id = loan.getId();
        this.book = new BookDTO(loan.getBook());
        this.user = new UserDTO(loan.getUser());
        this.startDate = loan.getStartDate();
        this.finalDate = loan.getFinalDate();
        this.status = loan.getStatus();
    }

    public LoanDTO(){}
}
