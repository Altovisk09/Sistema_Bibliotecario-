package com.library.dto.loan;

import com.library.model.LoanStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateLoanDTO {
    private Long bookId;
    private Long userId;
    @FutureOrPresent(message = "A data de início deve ser hoje ou no futuro.")
    private LocalDate startDate;
    @Future(message = "A data final deve ser uma data futura.")
    private LocalDate finalDate;
    private LoanStatus status;

    public UpdateLoanDTO(Long bookId, Long userId, LocalDate startDate, LocalDate finalDate, LoanStatus status){
        this.bookId = bookId;
        this.userId = userId;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.status = status;
    }

    public UpdateLoanDTO(){}
}
