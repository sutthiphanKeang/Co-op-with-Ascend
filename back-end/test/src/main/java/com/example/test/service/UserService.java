package com.example.test.service;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUser(){
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(User User) {
        return userRepository.save(User);
    }

    public Optional<User> updateUser(Long id, User user) {
        Optional<User> userData = userRepository.findById(id);
        if(!userData.isPresent()) {
            return userData;
        }
        return Optional.of(userRepository.save(user));
    }

    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
