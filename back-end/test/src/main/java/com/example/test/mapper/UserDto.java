package com.example.test.mapper;

import com.example.test.model.Invoice;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private Set<Invoice> invoices;

    @NotEmpty(message = "First name is required")
    private String f_name;

    @NotEmpty(message = "Last name is required")
    private String l_name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Phone number is required")
    private String phone_no;

    @NotEmpty(message = "Password is required")
    private String password;
}