package com.library.controller;

import com.library.dto.loan.CreateLoanDTO;
import com.library.dto.loan.LoanDTO;
import com.library.dto.loan.UpdateLoanDTO;
import com.library.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    private final static Logger logger = LoggerFactory.getLogger(LoanController.class);
    private final LoanService service;

    public LoanController(LoanService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@RequestBody @Valid CreateLoanDTO dto){
        logger.info("Recebida solicitação de criação de empréstimo para usuário: {}", dto.getUserId());
        LoanDTO loan = service.createLoan(dto);
        logger.info("Empréstimo criado com sucesso: id:{}, book:{}", loan.getId(), loan.getBook().getId());
        return ResponseEntity.status(201).body(loan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> findLoanById(@PathVariable Long id){
        logger.info("Recebida solicitação de dados referentes ao empréstimo de livro: {}", id);
        LoanDTO loan = service.findLoanById(id);
        logger.info("Empréstimo encontrado: id={}, bookId={}, userId={}",
                loan.getId(), loan.getBook().getId(), loan.getUser().getId());
        return ResponseEntity.status(200).body(loan);
    }

    @GetMapping
    public ResponseEntity<List<LoanDTO>> findAllLoan(){
        logger.info("Recebida solicitação de dados gerais referentes a empréstimos");
        List<LoanDTO> listLoan = service.listAllLoans();
        logger.info("Número total de empréstimos retornados: {}", listLoan.size());
        return ResponseEntity.status(200).body(listLoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanDTO> updateLoanById(@PathVariable Long id, @RequestBody UpdateLoanDTO dto){
        logger.info("Recebida solicitação de atualização de empréstimo: id={}, dados={}", id, dto);
        LoanDTO loan = service.updateLoan(id, dto);
        logger.info("Empréstimo atualizado com sucesso: id={}, bookId={}, userId={}",
                loan.getId(), loan.getBook().getId(), loan.getUser().getId());
        return ResponseEntity.status(200).body(loan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanById(@PathVariable Long id){
        logger.info("Recebida solicitação de exclusão de empréstimo: id={}", id);
        service.deleteLoan(id);
        logger.info("Empréstimo deletado com sucesso: id={}", id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<LoanDTO> returnLoan(@PathVariable Long id) {
        logger.info("Solicitação de devolução para empréstimo id={}", id);
        LoanDTO loan = service.returnLoan(id);
        logger.info("Empréstimo devolvido com sucesso: id={}", id);
        return ResponseEntity.ok(loan);
    }
}
