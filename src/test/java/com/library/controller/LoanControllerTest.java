package com.library.controller;

import com.library.dto.loan.CreateLoanDTO;
import com.library.dto.loan.LoanDTO;
import com.library.dto.loan.UpdateLoanDTO;
import com.library.model.LoanStatus;
import com.library.dto.book.BookDTO;
import com.library.dto.user.UserDTO;
import com.library.model.UserType;
import com.library.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanControllerTest {

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    private LoanDTO loanDTO;

    @BeforeEach
    void setUp() {
        BookDTO book = new BookDTO();
        book.setId(1L);
        book.setName("Clean Code");

        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setName("John Doe");
        user.setUserType(UserType.STUDENT_GRADUATION);

        loanDTO = new LoanDTO();
        loanDTO.setId(1L);
        loanDTO.setBook(book);
        loanDTO.setUser(user);
        loanDTO.setStartDate(LocalDate.now());
        loanDTO.setFinalDate(LocalDate.now().plusDays(7));
        loanDTO.setStatus(LoanStatus.ACTIVE);
    }

    @Test
    void shouldCreateLoan() {
        CreateLoanDTO createDto = new CreateLoanDTO(1L, 1L, LocalDate.now(), LocalDate.now().plusDays(7), LoanStatus.ACTIVE);

        when(loanService.createLoan(createDto)).thenReturn(loanDTO);

        ResponseEntity<LoanDTO> response = loanController.createLoan(createDto);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(loanDTO.getId(), response.getBody().getId());
        verify(loanService, times(1)).createLoan(createDto);
    }

    @Test
    void shouldFindLoanById() {
        when(loanService.findLoanById(1L)).thenReturn(loanDTO);

        ResponseEntity<LoanDTO> response = loanController.findLoanById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(loanService, times(1)).findLoanById(1L);
    }

    @Test
    void shouldFindAllLoans() {
        LoanDTO loan2 = new LoanDTO();
        loan2.setId(2L);

        when(loanService.listAllLoans()).thenReturn(List.of(loanDTO, loan2));

        ResponseEntity<List<LoanDTO>> response = loanController.findAllLoan();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(loanService, times(1)).listAllLoans();
    }

    @Test
    void shouldUpdateLoan() {
        UpdateLoanDTO updateDto = new UpdateLoanDTO();
        updateDto.setStatus(LoanStatus.RETURNED);

        loanDTO.setStatus(LoanStatus.RETURNED);
        when(loanService.updateLoan(1L, updateDto)).thenReturn(loanDTO);

        ResponseEntity<LoanDTO> response = loanController.updateLoanById(1L, updateDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(LoanStatus.RETURNED, response.getBody().getStatus());
        verify(loanService, times(1)).updateLoan(1L, updateDto);
    }

    @Test
    void shouldDeleteLoan() {
        doNothing().when(loanService).deleteLoan(1L);

        ResponseEntity<Void> response = loanController.deleteLoanById(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(loanService, times(1)).deleteLoan(1L);
    }
}
