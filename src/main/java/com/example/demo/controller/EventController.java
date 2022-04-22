package com.example.demo.controller;

import com.example.demo.entities.Account;
import com.example.demo.entities.AccountEvent;
import com.example.demo.entities.AccountEventId;
import com.example.demo.entities.Event;
import com.example.demo.models.AccountEventDto;
import com.example.demo.models.EventDto;
import com.example.demo.repository.AccountEventRepository;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("event")
public class EventController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    EventService eventService;
    @Autowired
    AccountEventRepository accountEventRepository;
    @GetMapping("/list")
    public ResponseEntity<List<EventDto>> findAll(){
        List<Event> eventList =  eventRepository.findAll();
        List<EventDto> eventDtoList = new ArrayList<>();
        for (Event event: eventList
        ) {
            EventDto eventDto= new EventDto();
            BeanUtils.copyProperties(event,eventDto);
            eventDtoList.add(eventDto);
        }
        return ResponseEntity.ok().body(eventDtoList);
    }

    @PostMapping("create")
    public ResponseEntity<EventDto> create(@RequestBody Event event){
        event=eventRepository.save(event);
        EventDto eventDto = new EventDto();
        BeanUtils.copyProperties(event,eventDto);
        return ResponseEntity.ok().body(eventDto);

    }
    @PutMapping ("update")
    public ResponseEntity<EventDto> update(@RequestBody  Event event){
        Optional<Event> event1= eventRepository.findById(event.getEventId());
        if(event1.isPresent()){
            eventRepository.save(event);
            EventDto eventDto = new EventDto();
            BeanUtils.copyProperties(event,eventDto);
            return ResponseEntity.ok().body(eventDto);
        }
        return ResponseEntity.notFound().build();



    }
    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        Optional<Event> event= eventRepository.findById(id);
        if(event.isPresent()) {
            eventRepository.deleteById(id);
            return ResponseEntity.ok().body(null);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("addOneMemberInEvent")
    public ResponseEntity<AccountEventDto> addOneMember(@RequestParam("idEvent") Long id, @RequestBody Account account){
        Boolean check= accountService.checkEventIsPresent(id, account.getAccountId());
        Optional<Event> event= eventRepository.findById(id);
        if(event.isPresent()==true ) {
            //
            List<AccountEvent> accountEventList = account.getAccountEventList();
            if (accountEventList.size() == 0) {
                // neu accountEvent ko co du lieu add thang vaof accountEvent
                AccountEventId accountEventId = new AccountEventId(account.getAccountId(),id);
                AccountEvent accountEvent= new AccountEvent();
                accountEvent.setAccountEventId(accountEventId);
                accountEvent.setEvents(event.get());
                accountEvent.setAccount(account);
                System.out.println(account +" \n "+ event.get()+" \n "+accountEventId);
                accountEventList.add(accountEvent);
                account.setAccountEventList(accountEventList);
                accountEventRepository.save(accountEvent);
                accountRepository.save(account);
                AccountEventDto accountEventDto= new AccountEventDto();
                BeanUtils.copyProperties(accountEvent,accountEventDto);
                return ResponseEntity.ok().body(accountEventDto);
            } else {
//            List<Event>eventList=accountEventList.stream().filter(accountEvent -> accountEvent.getEvents()).collect(Collectors.toList());
                List<Event> eventList = new ArrayList<>();
                for (AccountEvent ac : accountEventList
                ) {
                    eventList.add(ac.getEvents());
                }
                Boolean checkDuplicateEvent = eventService.checkTimeIsDuplicate(event.get().getDateCreate(), event.get().getDateEnd(), eventList);
                if (check == true && checkDuplicateEvent == true) {
                    return ResponseEntity.badRequest().body(new AccountEventDto());
                } else {
                    AccountEvent accountEvent= new AccountEvent(new AccountEventId(account.getAccountId(),id),account,event.get());
                    accountEventList.add(accountEvent);
                    account.setAccountEventList(accountEventList);
                    accountEventRepository.save(accountEvent);
                    accountRepository.save(account);
                    AccountEventDto accountEventDto= new AccountEventDto();
                    BeanUtils.copyProperties(accountEvent,accountEventDto);
                    return ResponseEntity.ok().body(accountEventDto);
                }
            }
//

        }
        return ResponseEntity.badRequest().body(new AccountEventDto());

    }
}
