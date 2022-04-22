package com.example.demo.models;

import com.example.demo.entities.Account;
import com.example.demo.entities.AccountEventId;
import com.example.demo.entities.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccountEventDto {
    private AccountEventIdDto accountEventIdDto;

    private AccountDto account;
    private EventDto events;
}
