package com.example.test.mapper;

import com.example.test.model.Invoice;
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
    private String f_name;
    private String l_name;
    private String email;
    private String phone_no;
    private String password;
}