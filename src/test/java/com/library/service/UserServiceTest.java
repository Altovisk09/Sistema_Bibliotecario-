package com.library.service;

import com.library.dto.user.CreateUserDTO;
import com.library.dto.user.UpdateUserDTO;
import com.library.dto.user.UserDTO;
import com.library.exceptions.NotFoundException;
import com.library.model.User;
import com.library.model.UserType;
import com.library.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUserSuccessfully() {
        CreateUserDTO dto = new CreateUserDTO("John Doe", UserType.STUDENT_GRADUATION);
        User savedUser = new User("John Doe", UserType.STUDENT_GRADUATION);
        savedUser.setId(1L);

        when(repository.save(any(User.class))).thenReturn(savedUser);

        UserDTO result = userService.createUser(dto);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals(UserType.STUDENT_GRADUATION, result.getUserType());
        assertEquals(1L, result.getId());

        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void shouldFindUserById() {
        User user = new User("Jane Doe", UserType.TEACHER);
        user.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO result = userService.findUserById(1L);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        assertEquals(UserType.TEACHER, result.getUserType());
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldThrowNotFoundWhenUserNotExist() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.findUserById(99L));
    }

    @Test
    void shouldUpdateUserSuccessfully() {
        User existing = new User("Old Name", UserType.STUDENT_GRADUATION);
        existing.setId(1L);

        UpdateUserDTO dto = new UpdateUserDTO("New Name", UserType.TEACHER);

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        UserDTO result = userService.updateUserById(1L, dto);

        assertEquals("New Name", result.getName());
        assertEquals(UserType.TEACHER, result.getUserType());
        verify(repository, times(1)).save(existing);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingWithNoFields() {
        User existing = new User("Old Name", UserType.STUDENT_GRADUATION);
        existing.setId(1L);

        UpdateUserDTO dto = new UpdateUserDTO(); // sem campos

        when(repository.findById(1L)).thenReturn(Optional.of(existing));

        assertThrows(IllegalArgumentException.class, () -> userService.updateUserById(1L, dto));
    }

    @Test
    void shouldDeleteUserSuccessfully() {
        User user = new User("John Doe", UserType.STUDENT_GRADUATION);
        user.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUserById(1L);

        verify(repository, times(1)).delete(user);
    }

    @Test
    void shouldThrowNotFoundWhenDeletingNonexistentUser() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.deleteUserById(99L));
    }

    @Test
    void shouldListAllUsers() {
        User user1 = new User("User1", UserType.STUDENT_GRADUATION);
        user1.setId(1L);
        User user2 = new User("User2", UserType.TEACHER);
        user2.setId(2L);

        when(repository.findAll()).thenReturn(List.of(user1, user2));

        List<UserDTO> result = userService.listAllUsers();

        assertEquals(2, result.size());
        assertEquals("User1", result.get(0).getName());
        assertEquals("User2", result.get(1).getName());
    }
}
