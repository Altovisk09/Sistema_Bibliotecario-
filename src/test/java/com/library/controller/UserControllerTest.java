package com.library.controller;

import com.library.dto.user.CreateUserDTO;
import com.library.dto.user.UpdateUserDTO;
import com.library.dto.user.UserDTO;
import com.library.model.UserType;
import com.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("John Doe");
        userDTO.setUserType(UserType.STUDENT_GRADUATION);
    }

    @Test
    void shouldCreateUser() {
        CreateUserDTO createDto = new CreateUserDTO("John Doe", UserType.STUDENT_GRADUATION);

        when(userService.createUser(createDto)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.createUser(createDto);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(userDTO.getId(), response.getBody().getId());
        verify(userService, times(1)).createUser(createDto);
    }

    @Test
    void shouldFindUserById() {
        when(userService.findUserById(1L)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.findUserById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userDTO.getId(), response.getBody().getId());
        verify(userService, times(1)).findUserById(1L);
    }

    @Test
    void shouldUpdateUser() {
        UpdateUserDTO updateDto = new UpdateUserDTO();
        updateDto.setName("Jane Doe");

        userDTO.setName("Jane Doe");
        when(userService.updateUserById(1L, updateDto)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.updateUserById(1L, updateDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Jane Doe", response.getBody().getName());
        verify(userService, times(1)).updateUserById(1L, updateDto);
    }

    @Test
    void shouldDeleteUser() {
        doNothing().when(userService).deleteUserById(1L);

        ResponseEntity<Void> response = userController.deleteUserById(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(userService, times(1)).deleteUserById(1L);
    }

    @Test
    void shouldListAllUsers() {
        UserDTO user2 = new UserDTO();
        user2.setId(2L);
        user2.setName("Jane Doe");

        when(userService.listAllUsers()).thenReturn(List.of(userDTO, user2));

        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(userService, times(1)).listAllUsers();
    }
}
