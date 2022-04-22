package com.example.demo.models;

import com.example.demo.entities.AccountEvent;
import com.example.demo.entities.Event;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private Long eventId;
    private String eventName;
    private Long dateCreate;
    private Long dateEnd;
    private List<AccountEvent> accountEventList;
}
