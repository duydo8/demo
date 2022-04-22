package com.example.demo.service;

import com.example.demo.entities.AccountEvent;
import com.example.demo.entities.AccountEventId;

import java.util.List;

public interface AccountEventService {
    AccountEvent save(AccountEvent accountEvent);
    boolean update(AccountEvent accountEvent);
    void delete(AccountEventId accountEventId);
    AccountEvent findById(AccountEventId accountEventId);
    List<AccountEvent> findAll();
}
