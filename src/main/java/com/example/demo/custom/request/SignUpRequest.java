package com.example.demo.custom.request;

import com.example.demo.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String username;

    private String password;
    private String email;
    private Role role;
}
