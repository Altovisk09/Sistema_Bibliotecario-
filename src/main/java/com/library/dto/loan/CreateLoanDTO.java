package com.library.dto.loan;
import com.library.model.LoanStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateLoanDTO {
    @NotNull(message = "O ID do livro é obrigatório.")
    private Long bookId;

    @NotNull(message = "O ID do usuário é obrigatório.")
    private Long userId;

    @NotNull(message = "A data de início é obrigatória.")
    @FutureOrPresent(message = "A data de início deve ser hoje ou uma data futura.")
    private LocalDate startDate;

    @NotNull(message = "A data final é obrigatória.")
    @Future(message = "A data final deve ser uma data futura.")
    private LocalDate finalDate;

    @NotNull(message = "O status do empréstimo é obrigatório.")
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
