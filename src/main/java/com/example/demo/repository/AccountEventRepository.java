package com.example.demo.repository;

import com.example.demo.entities.AccountEvent;
import com.example.demo.entities.AccountEventId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountEventRepository extends JpaRepository<AccountEvent, AccountEventId> {
}
