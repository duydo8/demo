package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="account_event")
public class AccountEvent {
    @EmbeddedId
    private AccountEventId accountEventId;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name="account_id")
    private  Account account = new Account();

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name="event_id")
    private   Event events = new Event();


}
