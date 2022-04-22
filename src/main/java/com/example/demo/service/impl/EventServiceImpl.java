package com.example.demo.service.impl;

import com.example.demo.entities.Event;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;
    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public boolean update(Event event) {
        Optional<Event> account1= eventRepository.findById(event.getEventId());
        if(account1.isPresent()){

            eventRepository.save(event);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void delete(Long id) {
    eventRepository.deleteById(id);
    }

    @Override
    public Event findById(Long id) {
        Optional<Event> event= eventRepository.findById(id);
        if(event.isPresent()){


            return event.get();
        }else{
            return null;
        }
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public boolean checkTimeIsDuplicate(Long eventStart, Long eventEnd, List<Event> eventList){
        List<Event> list=eventList.stream().sorted(Comparator.comparing(e->e.getDateCreate())).collect(Collectors.toList());
        for(int i=1;i<list.size()-1;i++){
//            eventStart<list.get(i).getDateEnd()&&eventEnd>list.get(i+1).getDateCreate()
            if(eventStart<list.get(i-1).getDateEnd()&&eventEnd>list.get(i).getDateCreate() || eventStart>list.get(eventList.size()).getDateEnd()
                    || eventEnd<list.get(0).getDateCreate()){
                return true;
            }
        }
        return  false;

    }
}
