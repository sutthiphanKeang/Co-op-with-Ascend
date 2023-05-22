package com.example.test.controller;

import com.example.test.mapper.UserDto;
import com.example.test.model.User;
import com.example.test.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;
    @Spy
    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUser() {
        List<User> users = new ArrayList<>();
        users.add(new User());

        when(userService.getUser()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUser();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(users, response.getBody());

        verify(userService, times(1)).getUser();
    }

    @Test
    void testGetAllUser_NoContent() {
        when(userService.getUser()).thenReturn(new ArrayList<>());

        ResponseEntity<List<User>> response = userController.getAllUser();

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        verify(userService, times(1)).getUser();
    }

    @Test
    void testGetUserById() {
        long userId = 1L;
        User user = new User();

        when(userService.getUser(userId)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserById(userId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(user, response.getBody());

        verify(userService, times(1)).getUser(userId);
    }

    @Test
    void testGetUserById_NotFound() {
        long userId = 1L;

        when(userService.getUser(userId)).thenReturn(null);

        ResponseEntity<User> response = userController.getUserById(userId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        verify(userService, times(1)).getUser(userId);
    }

    @Test
    void testCreateUser() {
        UserDto userDto = new UserDto();
        User user = new User();

        when(userService.createUser(userDto)).thenReturn(user);

        ResponseEntity<User> response = userController.createUser(userDto);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(user, response.getBody());

        verify(userService, times(1)).createUser(userDto);
    }

    @Test
    void testUpdateUser() {
        long userId = 1L;
        UserDto userDto = new UserDto();
        User user = new User();

        when(userService.updateUser(userId, userDto)).thenReturn(user);

        ResponseEntity<User> response = userController.updateUser(userId, userDto);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(user, response.getBody());

        verify(userService, times(1)).updateUser(userId, userDto);
    }

    @Test
    void testUpdateUser_NotFound() {
        long userId = 1L;
        UserDto userDto = new UserDto();

        when(userService.updateUser(userId, userDto)).thenReturn(null);

        ResponseEntity<User> response = userController.updateUser(userId, userDto);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        verify(userService, times(1)).updateUser(userId, userDto);
    }

    @Test
    void testDeleteUser() {
        long userId = 1L;

        when(userService.deleteUser(userId)).thenReturn(true);

        ResponseEntity<HttpStatus> response = userController.deleteUser(userId);

        Assertions.assertEquals(HttpStatus.GONE, response.getStatusCode());

        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    void testDeleteUser_NotFound() {
        long userId = 1L;

        when(userService.deleteUser(userId)).thenReturn(false);

        ResponseEntity<HttpStatus> response = userController.deleteUser(userId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(userService, times(1)).deleteUser(userId);
    }
}
