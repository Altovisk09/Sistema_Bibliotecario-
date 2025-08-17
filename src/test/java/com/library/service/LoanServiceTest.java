package com.library.service;

import com.library.dto.loan.CreateLoanDTO;
import com.library.dto.loan.LoanDTO;
import com.library.dto.loan.UpdateLoanDTO;
import com.library.exceptions.NotFoundException;
import com.library.model.*;
import com.library.repository.LoanRepository;
import com.library.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookService bookService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoanService loanService;

    @Test
    void shouldCreateLoanSuccessfully() {
        CreateLoanDTO dto = new CreateLoanDTO(1L, 1L,
                LocalDate.now(), LocalDate.now().plusDays(7), LoanStatus.ACTIVE);

        Book book = new Book("Clean Code", "Robert C. Martin", BookCategory.SCIENCE, 5);
        book.setId(1L);
        book.setAvailableCopies(5);

        User user = new User("John Doe", UserType.STUDENT_GRADUATION);
        user.setId(1L);

        when(bookService.getBookOrThrow(1L)).thenReturn(book);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(loanRepository.save(any(Loan.class))).thenAnswer(i -> i.getArguments()[0]);

        LoanDTO result = loanService.createLoan(dto);

        assertNotNull(result);
        assertEquals(dto.getBookId(), result.getBook().getId());
        assertEquals(dto.getUserId(), result.getUser().getId());
        assertEquals(LoanStatus.ACTIVE, result.getStatus());

        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    void shouldThrowWhenBookNotFoundOnCreate() {
        CreateLoanDTO dto = new CreateLoanDTO(99L, 1L,
                LocalDate.now(), LocalDate.now().plusDays(7), LoanStatus.ACTIVE);

        lenient().when(bookService.getBookOrThrow(99L))
                .thenThrow(new NotFoundException("Book not found"));

        assertThrows(NotFoundException.class, () -> loanService.createLoan(dto));
    }

    @Test
    void shouldThrowWhenUserNotFoundOnCreate() {
        CreateLoanDTO dto = new CreateLoanDTO(1L, 99L,
                LocalDate.now(), LocalDate.now().plusDays(7), LoanStatus.ACTIVE);

        Book book = new Book("Clean Code", "Robert C. Martin", BookCategory.SCIENCE, 5);
        book.setId(1L);
        book.setAvailableCopies(5);

        lenient().when(bookService.getBookOrThrow(1L)).thenReturn(book);
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> loanService.createLoan(dto));
    }

    @Test
    void shouldFindLoanById() {
        Book book = new Book("Book", "Author", BookCategory.SCIENCE, 3);
        book.setId(1L);

        User user = new User("John Doe", UserType.STUDENT_GRADUATION);
        user.setId(1L);

        Loan loan = new Loan();
        loan.setId(1L);
        loan.setBook(book);
        loan.setUser(user);
        loan.setStatus(LoanStatus.ACTIVE);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        LoanDTO result = loanService.findLoanById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(LoanStatus.ACTIVE, result.getStatus());
    }

    @Test
    void shouldThrowNotFoundWhenLoanNotExist() {
        when(loanRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> loanService.findLoanById(99L));
    }

    @Test
    void shouldUpdateLoanSuccessfully() {
        Book oldBook = new Book("Old Book", "Author", BookCategory.SCIENCE, 3);
        oldBook.setId(1L);

        User user = new User("Old User", UserType.TEACHER);
        user.setId(1L);

        Loan existing = new Loan();
        existing.setId(1L);
        existing.setStatus(LoanStatus.ACTIVE);
        existing.setBook(oldBook);
        existing.setUser(user);
        existing.setStartDate(LocalDate.now());
        existing.setFinalDate(LocalDate.now().plusDays(7));

        UpdateLoanDTO dto = new UpdateLoanDTO();
        dto.setStatus(LoanStatus.RETURNED);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(loanRepository.save(existing)).thenReturn(existing);

        LoanDTO result = loanService.updateLoan(1L, dto);

        assertEquals(LoanStatus.RETURNED, result.getStatus());
        verify(loanRepository, times(1)).save(existing);
    }

    @Test
    void shouldDeleteLoan() {
        Book book = new Book("Book", "Author", BookCategory.SCIENCE, 3);
        book.setId(1L);

        User user = new User("User", UserType.TEACHER);
        user.setId(1L);

        Loan loan = new Loan();
        loan.setId(1L);
        loan.setBook(book);
        loan.setUser(user);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        loanService.deleteLoan(1L);

        verify(loanRepository, times(1)).delete(loan);
    }

    @Test
    void shouldListAllLoans() {
        Book book1 = new Book("Book 1", "Author 1", BookCategory.SCIENCE, 3);
        book1.setId(1L);
        Book book2 = new Book("Book 2", "Author 2", BookCategory.SCIENCE, 2);
        book2.setId(2L);

        User user1 = new User("John Doe", UserType.STUDENT_GRADUATION);
        user1.setId(1L);
        User user2 = new User("Jane Doe", UserType.TEACHER);
        user2.setId(2L);

        Loan loan1 = new Loan();
        loan1.setId(1L);
        loan1.setBook(book1);
        loan1.setUser(user1);
        loan1.setStatus(LoanStatus.ACTIVE);

        Loan loan2 = new Loan();
        loan2.setId(2L);
        loan2.setBook(book2);
        loan2.setUser(user2);
        loan2.setStatus(LoanStatus.RETURNED);

        when(loanRepository.findAll()).thenReturn(List.of(loan1, loan2));

        List<LoanDTO> result = loanService.listAllLoans();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
    }
}
