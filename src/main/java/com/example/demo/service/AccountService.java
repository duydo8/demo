package com.example.demo.service;

import com.example.demo.entities.Account;
import com.example.demo.entities.Event;

import java.util.List;

public interface AccountService {
    Account save(Account account);
    boolean update(Account account);
    void delete(Long account);
    Account findById(Long account);
    List<Account> findAll();


    boolean checkEventIsPresent(Long idEvent, Long idAccount);

    Event checknEvenPresent(Long idEvent, Long idAccount);
}
