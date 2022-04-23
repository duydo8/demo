package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEventId implements Serializable {
    @Column(name="account_id")
    private Long accountId;
    @Column(name="event_id")
    private  Long eventId;
}
