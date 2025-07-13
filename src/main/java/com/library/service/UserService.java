package com.library.service;

import com.library.dto.user.CreateUserDTO;
import com.library.dto.user.UpdateUserDTO;
import com.library.dto.user.UserDTO;
import com.library.exceptions.NotFoundException;
import com.library.model.User;
import com.library.model.UserType;
import com.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
   private final UserRepository repository;

   public UserService(UserRepository repository){
       this.repository = repository;
   }

   @Transactional
   public UserDTO createUser(CreateUserDTO dto){
       User user = new User();
       user.setName(dto.getName());
       user.setUserType(dto.getUserType());

       user = repository.save(user);

       return new UserDTO(user);
   }

   public UserDTO findUserById(Long id){
      User user = repository.findById(id)
              .orElseThrow(()-> new NotFoundException(String.format("Usuário com o id %d não encontrado", id)));
    return new UserDTO(user);
   }
// Função dinamica, atualiza todos ou apenas os dados que forem recebidos na requisição.
    @Transactional
   public UserDTO updateUserById(Long id, UpdateUserDTO dto){
       User user = repository.findById(id)
               .orElseThrow(()-> new NotFoundException(String.format("Usuário com o id %d não encontrado", id)));

       if ((dto.getName() == null || dto.getName().isBlank()) && dto.getUserType() == null) {
           throw new IllegalArgumentException("Pelo menos um campo deve ser informado para atualização.");
       }

       if(dto.getName() != null && !dto.getName().isBlank()){
           user.setName(dto.getName());
       }

       if(dto.getUserType() != null){
           user.setUserType(dto.getUserType());
       }

       repository.save(user);

       return new UserDTO(user);
   }
    @Transactional
    public void deleteUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Usuário com o id %d não encontrado", id)));

        repository.delete(user);
    }
    public List<UserDTO> listAllUsers(){
       return repository.findAll().stream()
               .map(UserDTO::new)
               .toList();
    }
}
