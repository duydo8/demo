package com.example.demo.controller;

import com.example.demo.entities.Account;
import com.example.demo.models.AccountDto;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/list")
    public ResponseEntity<List<AccountDto>> findAll(){
        List<Account> accountList =  accountRepository.findAll();
        List<AccountDto> accountDtoList = new ArrayList<>();
        for (Account acc: accountList
             ) {
            AccountDto accountDto= new AccountDto();
            BeanUtils.copyProperties(acc,accountDto);
            accountDtoList.add(accountDto);
        }
        return ResponseEntity.ok().body(accountDtoList);
    }

    @PostMapping ("create")
    public ResponseEntity<AccountDto> create(@RequestBody  Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account=accountRepository.save(account);
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(account,accountDto);
        return ResponseEntity.ok().body(accountDto);

    }
    @PutMapping ("update")
    public ResponseEntity<AccountDto> update(@RequestBody  Account account){
        Optional<Account> account1= accountRepository.findById(account.getAccountId());
        if(account1.isPresent()){
            accountRepository.save(account);
            AccountDto accountDto = new AccountDto();
            BeanUtils.copyProperties(account,accountDto);
            return ResponseEntity.ok().body(accountDto);
        }
        return ResponseEntity.notFound().build();



    }
    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        Optional<Account> account1= accountRepository.findById(id);
        if(account1.isPresent()) {
            accountRepository.deleteById(id);
            return ResponseEntity.ok().body(null);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
