package com.library.controller;

import com.library.dto.user.CreateUserDTO;
import com.library.dto.user.UpdateUserDTO;
import com.library.dto.user.UserDTO;
import com.library.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserDTO dto) {
        logger.info("Recebida solicitação de criação de usuário: {}", dto.getName());
        UserDTO user = service.createUser(dto);
        logger.info("Usuário criado com sucesso: id={}, username={}", user.getId(), user.getName());

        return ResponseEntity.status(201).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id) {
        logger.info("Recebida solicitação de busca de usuário: id={}", id);
        UserDTO user = service.findUserById(id);
        logger.info("Usuário encontrado: id={}, username={}", user.getId(), user.getName());
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Long id, @RequestBody UpdateUserDTO dto){
        logger.info("Recebida solicitação de atualização de usuário: id={}, dados={}", id, dto);
        UserDTO user = service.updateUserById(id, dto);
        logger.info("Usuário atualizado com sucesso: id={}, username={}", user.getId(), user.getName());
        return ResponseEntity.status(200).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        logger.info("Recebida solicitação de exclusão de usuário: id={}", id);
        service.deleteUserById(id);
        logger.info("Usuário deletado com sucesso: id={}", id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        logger.info("Recebida solicitação de listagem de todos os usuários");
        List<UserDTO> users = service.listAllUsers();
        logger.info("Número total de usuários retornados: {}", users.size());

        return ResponseEntity.ok(users);
    }
}
