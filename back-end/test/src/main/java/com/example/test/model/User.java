package com.example.test.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "Users", schema = "dbo", catalog = "test")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "user")
    private Set<Invoice> invoices;

    @Column(name = "f_name")
    @JsonProperty("f_name")
    private String fName;

    @Column(name = "l_name")
    @JsonProperty("l_name")
    private String lName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "password")
    private String password;

}
