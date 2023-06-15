package com.example.test.service;

import com.example.test.exception.ExceptionResolver;
import com.example.test.mapper.UserDto;
import com.example.test.model.User;
import com.example.test.repository.InvoiceRepository;
import com.example.test.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ExceptionResolver.NotFoundException("ID: " + id + " Not Found."));
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ExceptionResolver.NotFoundException("Email: " + email + " Not Found."));
    }

    public User createUser(UserDto userDto) {
        Optional<User> userData = userRepository.findByEmail(userDto.getEmail());
        if (ObjectUtils.isEmpty(userData)) {
            User user = modelMapper.map(userDto, User.class);
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhoneNo(userDto.getPhoneNo());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            return userRepository.save(user);
        }
        throw new ExceptionResolver.NotFoundException("Email: " + userDto.getEmail() + " already registered");
    }

    public User updateUser(Long id, UserDto userDto) {
        User userData = userRepository.findById(id).orElseThrow(() -> new ExceptionResolver.NotFoundException("ID: " + id + " Not Found."));
        userData.setFirstName(userDto.getFirstName());
        userData.setLastName(userDto.getLastName());
        userData.setPhoneNo(userDto.getPhoneNo());
        userData.setEmail(userDto.getEmail());
        userData.setPassword(userDto.getPassword());
        return userRepository.save(userData);
    }

    public boolean deleteUser(Long id) {
        User userData = userRepository.findById(id).orElseThrow(() -> new ExceptionResolver.NotFoundException("ID: " + id + " Not Found."));
        userRepository.deleteById(userData.getId());
        return true;
    }
}
