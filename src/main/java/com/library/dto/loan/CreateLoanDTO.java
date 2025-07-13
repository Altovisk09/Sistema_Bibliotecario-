package com.library.dto.loan;
import com.library.model.LoanStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateLoanDTO {
    @NotNull
    private Long bookId;
    @NotNull
    private Long userId;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate finalDate;
    @NotNull
    private LoanStatus status;

    public CreateLoanDTO(Long bookId, Long userId, LocalDate startDate, LocalDate finalDate, LoanStatus status){
        this.bookId = bookId;
        this.userId = userId;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.status = status;
    }

    public CreateLoanDTO(){}
}
