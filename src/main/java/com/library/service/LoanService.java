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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookService bookService;

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
                .orElseThrow(() -> {
                    logger.error("Usuário não encontrado: userId={}", dto.getUserId());
                    return new NotFoundException("Usuário não encontrado");
                });

        Book book = bookService.getBookOrThrow(dto.getBookId());

        // Verifica cópias disponíveis
        try {
            bookService.decrementAvailableCopies(book.getId());
        } catch (Exception e) {
            logger.error("Falha ao decrementar cópias do livro: bookId={}, erro={}", book.getId(), e.getMessage());
            throw e;
        }

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
                    .orElseThrow(() -> {
                        logger.error("Usuário não encontrado ao atualizar empréstimo: userId={}", dto.getUserId());
                        return new NotFoundException("Usuário não encontrado");
                    });
            loan.setUser(user);
        }

        if (dto.getBookId() != null) {
            Book oldBook = loan.getBook();
            Book newBook = bookService.getBookOrThrow(dto.getBookId());

            try {
                bookService.incrementAvailableCopies(oldBook.getId());
                bookService.decrementAvailableCopies(newBook.getId());
            } catch (Exception e) {
                logger.error("Falha ao atualizar livro do empréstimo: oldBookId={}, newBookId={}, erro={}",
                        oldBook.getId(), newBook.getId(), e.getMessage());
                throw e;
            }

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

        try {
            bookService.incrementAvailableCopies(loan.getBook().getId());
        } catch (Exception e) {
            logger.error("Falha ao incrementar cópias do livro ao deletar empréstimo: bookId={}, erro={}",
                    loan.getBook().getId(), e.getMessage());
            throw e;
        }

        loanRepository.delete(loan);
    }

    public List<LoanDTO> listAllLoans() {
        return loanRepository.findAll().stream()
                .map(LoanDTO::new)
                .toList();
    }

    private Loan getLoanOrThrow(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Empréstimo não encontrado: loanId={}", id);
                    return new NotFoundException("Empréstimo não encontrado com id: " + id);
                });
    }
}
