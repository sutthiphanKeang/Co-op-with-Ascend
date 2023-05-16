package com.example.test.controller;

import com.example.test.exceptions.GlobalExceptionHandler;
import com.example.test.mapper.UserDto;
import com.example.test.model.User;
import com.example.test.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@ControllerAdvice(basePackageClasses = GlobalExceptionHandler.class)
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUser() {
        try {
            if (userService.getUser().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(userService.getUser(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        try {
            Optional<User> userData = userService.getUser(id);
        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
        try {
            User user = modelMapper.map(userDto, User.class);
            user.setF_name(userDto.getF_name());
            user.setL_name(userDto.getL_name());
            user.setPhone_no(userDto.getPhone_no());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            User userPost = userService.createUser(user);
            return new ResponseEntity<>(userPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@Valid @PathVariable("id") long id, @RequestBody UserDto userDto) {
        try {
            Optional<User> userData = userService.getUser(id);

        if (userData.isPresent()) {
            User user = modelMapper.map(userDto, User.class);
            user.setF_name(userDto.getF_name());
            user.setL_name(userDto.getL_name());
            user.setPhone_no(userDto.getPhone_no());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            Optional<User> userPut = userService.updateUser(id, user);
            return new ResponseEntity<>(userPut.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            if (userService.deleteUser(id)) {
                return new ResponseEntity<>(HttpStatus.GONE);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
