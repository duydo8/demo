package com.example.demo.service.impl;

import com.example.demo.entities.Account;
import com.example.demo.entities.AccountEvent;
import com.example.demo.entities.AccountEventId;
import com.example.demo.repository.AccountEventRepository;
import com.example.demo.service.AccountEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountEventServiceImpl implements AccountEventService {
    @Autowired
    AccountEventRepository accountEventRepository;
    @Override
    public AccountEvent save(AccountEvent accountEvent) {
        return accountEventRepository.save(accountEvent);
    }

    @Override
    public boolean update(AccountEvent accountEvent) {
        Optional<AccountEvent> accountEvent1= accountEventRepository.findById(accountEvent.getAccountEventId());
        if(accountEvent1.isPresent()){

            accountEventRepository.save(accountEvent);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void delete(AccountEventId accountEventId) {

        accountEventRepository.deleteById(accountEventId);

    }

    @Override
    public AccountEvent findById(AccountEventId accountEventId) {
        Optional<AccountEvent> accountEvent= accountEventRepository.findById(accountEventId);
        if(accountEvent.isPresent()){

            return accountEvent.get();
        }else{
            return null;
        }
    }

    @Override
    public List<AccountEvent> findAll() {
        return accountEventRepository.findAll();
    }


}
