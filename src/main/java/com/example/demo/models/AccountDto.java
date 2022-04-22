package com.example.demo.models;

import com.example.demo.entities.AccountEvent;
import com.example.demo.entities.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long accountId;
    private String username;
    private String password;
    private String email;
    List<AccountEvent> accountEventList;

}
