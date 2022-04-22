package com.example.demo.service.impl;

import com.example.demo.entities.Account;
import com.example.demo.entities.AccountEvent;
import com.example.demo.entities.Event;

import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    EventRepository eventRepository;
    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public boolean update(Account account) {
        Optional<Account> account1= accountRepository.findById(account.getAccountId());
        if(account1.isPresent()){

            accountRepository.save(account);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void delete(Long account1) {

        accountRepository.deleteById(account1);

    }

    @Override
    public Account findById(Long EventId) {
        Optional<Account> account= accountRepository.findById(EventId);
        if(account.isPresent()){

            return account.get();
        }else{
            return null;
        }
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean checkEventIsPresent(Long idEvent, Long idAccount){
        Optional<Account> acc= accountRepository.findById(idAccount);
        Optional<Event> event =eventRepository.findById(idEvent);
        if(acc.isPresent()&& event.isPresent()){
            Optional<AccountEvent> accountEvent=acc.get().getAccountEventList().stream().filter(e->e.getEvents().equals(event.get())).findFirst();
            if(accountEvent.isPresent()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public Event checknEvenPresent(Long idEvent, Long idAccount){
        return  accountRepository.checknEvenPresent(idEvent,idAccount);
    }



}
