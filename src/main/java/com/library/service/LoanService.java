package com.library.service;

import com.library.dto.loan.CreateLoanDTO;
import com.library.dto.loan.LoanDTO;
import com.library.dto.loan.UpdateLoanDTO;
import com.library.exceptions.NotFoundException;
import com.library.model.Book;
import com.library.model.Loan;
import com.library.model.User;
import com.library.repository.LoanRepository;
import com.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookService bookService;  // usa serviço, não repo diretamente

    public LoanService(LoanRepository loanRepository,
                       UserRepository userRepository,
                       BookService bookService) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    @Transactional
    public LoanDTO createLoan(CreateLoanDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        Book book = bookService.getBookOrThrow(dto.getBookId());

        // Verifica cópias disponíveis
        bookService.decrementAvailableCopies(book.getId());

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setStartDate(dto.getStartDate());
        loan.setFinalDate(dto.getFinalDate());
        loan.setStatus(dto.getStatus());

        loanRepository.save(loan);
        return new LoanDTO(loan);
    }

    public LoanDTO findLoanById(Long id) {
        Loan loan = getLoanOrThrow(id);
        return new LoanDTO(loan);
    }

    @Transactional
    public LoanDTO updateLoan(Long id, UpdateLoanDTO dto) {
        Loan loan = getLoanOrThrow(id);

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
            loan.setUser(user);
        }

        if (dto.getBookId() != null) {
            Book oldBook = loan.getBook();
            Book newBook = bookService.getBookOrThrow(dto.getBookId());

            // Ajusta cópias disponíveis
            bookService.incrementAvailableCopies(oldBook.getId());
            bookService.decrementAvailableCopies(newBook.getId());

            loan.setBook(newBook);
        }

        if (dto.getStartDate() != null) {
            loan.setStartDate(dto.getStartDate());
        }

        if (dto.getFinalDate() != null) {
            loan.setFinalDate(dto.getFinalDate());
        }

        if (dto.getStatus() != null) {
            loan.setStatus(dto.getStatus());
        }

        loanRepository.save(loan);
        return new LoanDTO(loan);
    }

    @Transactional
    public void deleteLoan(Long id) {
        Loan loan = getLoanOrThrow(id);
        bookService.incrementAvailableCopies(loan.getBook().getId());
        loanRepository.delete(loan);
    }

    public List<LoanDTO> listAllLoans() {
        return loanRepository.findAll().stream()
                .map(LoanDTO::new)
                .toList();
    }

    private Loan getLoanOrThrow(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empréstimo não encontrado com id: " + id));
    }
}

