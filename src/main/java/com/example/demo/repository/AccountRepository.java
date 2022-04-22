package com.example.demo.repository;

import com.example.demo.entities.Account;
import com.example.demo.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query(value = "select e from accounts ac join account_event ae on ac.account_id = ae.account_id \n" +
            "join events e on ae.event_id=e.event_id",nativeQuery = true)
    Event checknEvenPresent(Long idEvent, Long idAccount);
}
