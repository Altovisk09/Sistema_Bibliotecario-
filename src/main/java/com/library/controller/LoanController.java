package com.library.controller;

import com.library.dto.loan.CreateLoanDTO;
import com.library.dto.loan.LoanDTO;
import com.library.dto.loan.UpdateLoanDTO;
import com.library.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    private final LoanService service;

    public LoanController(LoanService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@RequestBody @Valid CreateLoanDTO dto){
        LoanDTO loan = service.createLoan(dto);
        return ResponseEntity.status(201).body(loan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> findLoanById(@PathVariable Long id){
        LoanDTO loan = service.findLoanById(id);
        return ResponseEntity.status(200).body(loan);
    }

    @GetMapping
    public ResponseEntity<List<LoanDTO>> findAllLoan(){
        List<LoanDTO> listLoan = service.listAllLoans();
        return ResponseEntity.status(200).body(listLoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanDTO> updateLoanById(@PathVariable Long id, @RequestBody UpdateLoanDTO dto){
        LoanDTO loan = service.updateLoan(id, dto);
        return ResponseEntity.status(200).body(loan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanById(@PathVariable Long id){
        service.deleteLoan(id);
        return ResponseEntity.status(204).build();
    }

}
