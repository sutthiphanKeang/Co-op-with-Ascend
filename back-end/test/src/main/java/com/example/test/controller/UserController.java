//package com.example.test.controller;
//
//import com.example.test.exception.GlobalExceptionHandler;
//import com.example.test.mapper.UserDto;
//import com.example.test.model.User;
//import com.example.test.service.UserService;
//import javax.validation.Valid;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin
//@ControllerAdvice(basePackageClasses = GlobalExceptionHandler.class)
//public class UserController {
//
//    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//    @Autowired
//    UserService userService;
//
//    @GetMapping("/get-user")
//    public List<User> getAllUser() {
////        try {
////            List<User> userData = userService.getUser();
////            if (ObjectUtils.isEmpty(userData)) {
////                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////            }
////            else {
////                return new ResponseEntity<>(userData, HttpStatus.OK);
////            }
////        } catch (Exception e) {
////            logger.error("An error occurred while getting all of the user: {}", e.getMessage());
////            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
//        return userService.getUser();
//    }
//
//    @GetMapping("/get-user/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
//        try {
//            User userData = userService.getUser(id);
//            if (ObjectUtils.isEmpty(userData)) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            } else {
//                return new ResponseEntity<>(userData, HttpStatus.OK);
//            }
//        } catch (Exception e) {
//            logger.error("An error occurred while getting the user: {}", e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping("/post-user")
//    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
//        try {
//            User userPost = userService.createUser(userDto);
//            return new ResponseEntity<>(userPost, HttpStatus.CREATED);
//        } catch (Exception e) {
//            logger.error("An error occurred while create the user: {}", e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/put-user/{id}")
//    public ResponseEntity<User> updateUser(@Valid @PathVariable("id") long id, @RequestBody UserDto userDto) {
//        try {
//            User userData = userService.updateUser(id, userDto);
//            if (ObjectUtils.isEmpty(userData)) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            } else {
//                return new ResponseEntity<>(userData, HttpStatus.OK);
//            }
//        } catch (Exception e) {
//            logger.error("An error occurred while update the user: {}", e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/delete-user/{id}")
//    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
//        try {
//            if (userService.deleteUser(id)) {
//                return new ResponseEntity<>(HttpStatus.GONE);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            logger.error("An error occurred while delete the user: {}", e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
