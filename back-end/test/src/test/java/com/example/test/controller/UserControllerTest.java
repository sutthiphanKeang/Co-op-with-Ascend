package com.example.test.controller;

import com.example.test.mapper.UserDto;
import com.example.test.model.User;
import com.example.test.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    private static String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void testGetAllUser() throws Exception {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setFirstName("keang");
        user.setLastName("55555");
        user.setEmail("test@test.com");
        users.add(user);

        when(userService.getUser()).thenReturn(users);

        mockMvc.perform(get("/api/get-user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(user.getId()))
                .andExpect(jsonPath("$[0].firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(user.getLastName()))
                .andExpect(jsonPath("$[0].email").value(user.getEmail()));

        users.clear();
        mockMvc.perform(get("/api/get-user"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetUserById() throws Exception {
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setFirstName("keang");
        user.setLastName("55555");
        user.setEmail("test@test.com");

        when(userService.getUser(userId)).thenReturn(user);

        mockMvc.perform(get("/api/get-user/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));

        when(userService.getUser(userId)).thenReturn(null);

        mockMvc.perform(get("/api/get-user/{id}", userId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("keang");
        userDto.setLastName("55555");
        userDto.setEmail("test@test.com");
        userDto.setPhoneNo("1234567890");
        userDto.setPassword("12345678");

        User user = new User();
        user.setId(1L);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setPassword(userDto.getPassword());

        when(userService.createUser(any(UserDto.class))).thenReturn(user);

        mockMvc.perform(post("/api/post-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()))
                .andExpect(jsonPath("$.phoneNo").value(userDto.getPhoneNo()))
                .andExpect(jsonPath("$.password").value(userDto.getPassword()));
    }

    @Test
    void testUpdateUser() throws Exception {
        long userId = 1L;
        UserDto userDto = new UserDto();
        userDto.setFirstName("keang");
        userDto.setLastName("66666");
        userDto.setEmail("test@test.com");
        userDto.setPhoneNo("1234567890");
        userDto.setPassword("12345678");

        User user = new User();
        user.setId(userId);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setPassword(userDto.getPassword());

        when(userService.updateUser(eq(userId), any(UserDto.class))).thenReturn(user);

        mockMvc.perform(put("/api/put-user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()))
                .andExpect(jsonPath("$.phoneNo").value(userDto.getPhoneNo()))
                .andExpect(jsonPath("$.password").value(userDto.getPassword()));

        when(userService.updateUser(eq(userId), any(UserDto.class))).thenReturn(null);

        mockMvc.perform(put("/api/put-user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteUser() throws Exception {
        long userId = 1L;

        when(userService.deleteUser(userId)).thenReturn(true);

        mockMvc.perform(delete("/api/delete-user/{id}", userId))
                .andExpect(status().isGone());

        when(userService.deleteUser(userId)).thenReturn(false);

        mockMvc.perform(delete("/api/delete-user/{id}", userId))
                .andExpect(status().isNotFound());
    }
}
