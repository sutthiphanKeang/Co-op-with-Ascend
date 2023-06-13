//package com.example.test.graphqls;
//
//import com.example.test.model.User;
//import com.graphql.spring.boot.test.GraphQLTestTemplate;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
//import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.graphql.test.tester.GraphQlTester;
//
//import java.util.List;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureGraphQlTester
//@GraphQlTest
//class UserGraphQlTest {
//
//    @Autowired
//    private GraphQlTester tester;
//
//    @Autowired
//    private GraphQLTestTemplate graphQLTestTemplate;
//
//    @Test
//    void getAllUsers() {
//        String query = " query { getAllUsers {id firstName lastName phoneNo email password} } ";
//        List<User> users = tester.document(query)
//                .execute()
//                .path("getAllUsers")
//                .entityList(User.class)
//                .get();
//        Assertions.assertTrue(users.size() > 0);
//        Assertions.assertNotNull(users.get(0).getId());
//        Assertions.assertNotNull(users.get(0).getFirstName());
//        Assertions.assertNotNull(users.get(0).getLastName());
//        Assertions.assertNotNull(users.get(0).getPhoneNo());
//        Assertions.assertNotNull(users.get(0).getEmail());
//        Assertions.assertNotNull(users.get(0).getPassword());
//    }
//
//    @Test
//    void getUserById() {
//        String query = " query { getUserById(id:\"27\") {id firstName lastName phoneNo email password} } ";
//        User user = tester.document(query)
//                .execute()
//                .path("getUserById")
//                .entity(User.class)
//                .get();
//        Assertions.assertNotNull(user);
//        Assertions.assertNotNull(user.getId());
//        Assertions.assertNotNull(user.getFirstName());
//        Assertions.assertNotNull(user.getLastName());
//        Assertions.assertNotNull(user.getPhoneNo());
//        Assertions.assertNotNull(user.getEmail());
//        Assertions.assertNotNull(user.getPassword());
//    }
//
//    @Test
//    void createUser() {
//        String query = """
//                mutation { createUser ( userDto: {
//                    firstName: "create"
//                    lastName: "test_graphQl"
//                    phoneNo: "1234567890"
//                    email: "test_ql@gmail.com"
//                    password: "123456"
//                  }) {
//                    id
//                    firstName
//                    lastName
//                    phoneNo
//                    email
//                    password
//                  }
//                }""";
//        User user = tester.document(query)
//                .execute()
//                .path("createUser")
//                .entity(User.class)
//                .get();
//        Assertions.assertNotNull(user);
//        Assertions.assertNotNull(user.getId());
//        Assertions.assertNotNull(user.getFirstName());
//        Assertions.assertNotNull(user.getLastName());
//        Assertions.assertNotNull(user.getPhoneNo());
//        Assertions.assertNotNull(user.getEmail());
//        Assertions.assertNotNull(user.getPassword());
//    }
//
//    @Test
//    void updateUser() {
//        String query = """
//                mutation {
//                   updateUser(id:27, userDto: {
//                     firstName: "update"
//                     lastName: "test_graphQl"
//                     phoneNo: "1234567890"
//                     email: "test_ql@gmail.com"
//                     password: "123456"
//                   }) {
//                     id
//                     firstName
//                     lastName
//                     phoneNo
//                     email
//                     password
//                   }
//                 }""";
//        User user = tester.document(query)
//                .execute()
//                .path("updateUser")
//                .entity(User.class)
//                .get();
//        Assertions.assertNotNull(user);
//        Assertions.assertNotNull(user.getId());
//        Assertions.assertNotNull(user.getFirstName());
//        Assertions.assertNotNull(user.getLastName());
//        Assertions.assertNotNull(user.getPhoneNo());
//        Assertions.assertNotNull(user.getEmail());
//        Assertions.assertNotNull(user.getPassword());
//    }
//
//    @Test
//    void deleteUser() {
//        String query = """
//                mutation {
//                   deleteUser(id:29)
//                 }""";
//        Boolean user = tester.document(query)
//                .execute()
//                .path("deleteUser")
//                .entity(Boolean.class)
//                .get();
//        Assertions.assertTrue(user);
//    }
//}
