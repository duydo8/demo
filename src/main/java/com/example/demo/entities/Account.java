package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<AccountEvent> accountEventList = new ArrayList<>() ;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private  List<RoleAccount> roleAccountList;




}
