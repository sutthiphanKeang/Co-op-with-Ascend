package com.example.test.resolver;

import com.example.test.exception.GlobalExceptionHandler;
import com.example.test.mapper.UserDto;
import com.example.test.model.User;
import com.example.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin
@AllArgsConstructor
@SchemaMapping(typeName = "User")
@ControllerAdvice(basePackageClasses = GlobalExceptionHandler.class)
public class UserResolver {

    @Autowired
    private UserService userService;

    @QueryMapping
    public List<User> getAllUsers() {
        return userService.getUser();
    }

    @QueryMapping
    public User getUserById(@Argument("id") long id) {
        return userService.getUser(id);
    }

    @QueryMapping
    public User getUserByEmail(@Argument("email") String email) {
        return userService.getUser(email);
    }

    @MutationMapping
    public User createUser(@Valid @Argument UserDto userDto) {
        return userService.createUser(userDto);
    }

    @MutationMapping
    public User updateUser(@Valid @Argument("id") long id, @Argument UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument("id") long id) {
        return userService.deleteUser(id);
    }
}
