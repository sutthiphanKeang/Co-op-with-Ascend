//package com.example.test.service;
//
//import com.example.test.exception.ExceptionResolver;
//import com.example.test.mapper.UserDto;
//import com.example.test.model.User;
//import com.example.test.repository.UserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.dao.EmptyResultDataAccessException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private UserService userService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetUser() {
//        List<User> expectedUsers = new ArrayList<>();
//        User user = new User();
//        user.setId(1L);
//        user.setFirstName("keang");
//        user.setLastName("55555");
//        user.setEmail("test@test.com");
//        expectedUsers.add(user);
//
//        when(userRepository.findAll()).thenReturn(expectedUsers);
//
//        List<User> actualUsers = userService.getUser();
//
//        Assertions.assertEquals(expectedUsers, actualUsers);
//        verify(userRepository, times(1)).findAll();
//    }
//
//    @Test
//    void testGetUserById() {
//        Long userId = 1L;
//        User expectedUser = new User();
//        expectedUser.setId(userId);
//        expectedUser.setFirstName("keang");
//        expectedUser.setLastName("55555");
//        expectedUser.setEmail("test@test.com");
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));
//
//        User actualUser = userService.getUser(userId);
//
//        when(userRepository.findById(userId)).thenThrow(ExceptionResolver.NotFoundException.class);
//
//        Assertions.assertThrows(ExceptionResolver.NotFoundException.class, () -> userService.getUser(userId));
//        Assertions.assertEquals(expectedUser, actualUser);
//        verify(userRepository, times(2)).findById(userId);
//    }
//
//    @Test
//    void testCreateUser() {
//        UserDto userDto = new UserDto();
//        userDto.setFirstName("keang");
//        userDto.setLastName("55555");
//        userDto.setEmail("test@test.com");
//        userDto.setPhoneNo("1234567890");
//        userDto.setPassword("12345678");
//
//        User expectedUser = new User();
//        expectedUser.setId(1L);
//        expectedUser.setFirstName(userDto.getFirstName());
//        expectedUser.setLastName(userDto.getLastName());
//        expectedUser.setEmail(userDto.getEmail());
//        expectedUser.setPhoneNo(userDto.getPhoneNo());
//        expectedUser.setPassword(userDto.getPassword());
//
//        when(modelMapper.map(userDto, User.class)).thenReturn(expectedUser);
//        when(userRepository.save(any(User.class))).thenReturn(expectedUser);
//
//        User actualUser = userService.createUser(userDto);
//
//        Assertions.assertEquals(expectedUser, actualUser);
//        verify(modelMapper, times(1)).map(userDto, User.class);
//        verify(userRepository, times(1)).save(expectedUser);
//    }
//
//    @Test
//    void testUpdateUser() {
//        Long userId = 1L;
//        UserDto userDto = new UserDto();
//        userDto.setFirstName("keang");
//        userDto.setLastName("55555");
//        userDto.setEmail("test@test.com");
//        userDto.setPhoneNo("1234567890");
//        userDto.setPassword("12345678");
//
//        User existingUser = new User();
//        existingUser.setId(userId);
//        existingUser.setFirstName(userDto.getFirstName());
//        existingUser.setLastName(userDto.getLastName());
//        existingUser.setEmail(userDto.getEmail());
//        existingUser.setPhoneNo(userDto.getPhoneNo());
//        existingUser.setPassword(userDto.getPassword());
//
//        User expectedUser = new User();
//        expectedUser.setId(userId);
//        expectedUser.setFirstName(userDto.getFirstName());
//        expectedUser.setLastName(userDto.getLastName());
//        expectedUser.setEmail(userDto.getEmail());
//        expectedUser.setPhoneNo(userDto.getPhoneNo());
//        expectedUser.setPassword(userDto.getPassword());
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
//        when(userRepository.save(any(User.class))).thenReturn(expectedUser);
//
//        User actualUser = userService.updateUser(userId, userDto);
//
//        when(userRepository.findById(userId)).thenThrow(ExceptionResolver.NotFoundException.class);
//
//        Assertions.assertThrows(ExceptionResolver.NotFoundException.class, () -> userService.updateUser(userId, userDto));
//        Assertions.assertEquals(expectedUser, actualUser);
//        verify(userRepository, times(2)).findById(userId);
//        verify(userRepository, times(1)).save(expectedUser);
//    }
//
//    @Test
//    void testDeleteUser() {
//        Long userId = 1L;
//        User expectedUser = new User();
//        expectedUser.setId(userId);
//        expectedUser.setFirstName("keang");
//        expectedUser.setLastName("55555");
//        expectedUser.setEmail("test@test.com");
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));
//
//        boolean resultTrue = userService.deleteUser(userId);
//
//        when(userRepository.findById(userId)).thenThrow(ExceptionResolver.NotFoundException.class);
//
//        Assertions.assertThrows(ExceptionResolver.NotFoundException.class, () -> userService.deleteUser(userId));
//        Assertions.assertTrue(resultTrue);
//        verify(userRepository, times(1)).deleteById(userId);
//    }
//
//}
