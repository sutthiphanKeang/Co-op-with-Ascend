package com.example.test.service;

import com.example.test.mapper.UserDto;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Spy
    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUser() {
        User user1 = new User();
        User user2 = new User();
        List<User> expectedUsers = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.getUser();

        Assertions.assertEquals(expectedUsers, actualUsers);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        User expectedUser = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.getUser(userId);

        Assertions.assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testCreateUser() {
        UserDto userDto = new UserDto();
        User expectedUser = new User();

        when(modelMapper.map(userDto, User.class)).thenReturn(expectedUser);
        when(userRepository.save(expectedUser)).thenReturn(expectedUser);

        User actualUser = userService.createUser(userDto);

        Assertions.assertEquals(expectedUser, actualUser);
        verify(modelMapper, times(1)).map(userDto, User.class);
        verify(userRepository, times(1)).save(expectedUser);
    }

    @Test
    void testUpdateUser() {
        Long userId = 1L;
        UserDto userDto = new UserDto();
        User existingUser = new User();
        User expectedUser = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(modelMapper.map(userDto, User.class)).thenReturn(expectedUser);
        when(userRepository.save(expectedUser)).thenReturn(expectedUser);

        User actualUser = userService.updateUser(userId, userDto);

        Assertions.assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).findById(userId);
        verify(modelMapper, times(1)).map(userDto, User.class);
        verify(userRepository, times(1)).save(expectedUser);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        doNothing().when(userRepository).deleteById(userId);

        boolean result = userService.deleteUser(userId);

        Assertions.assertTrue(result);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUser_NotFound() {
        Long userId = 1L;

        doThrow(new EmptyResultDataAccessException(1)).when(userRepository).deleteById(userId);

        boolean result = userService.deleteUser(userId);

        Assertions.assertFalse(result);
        verify(userRepository, times(1)).deleteById(userId);
    }
}
