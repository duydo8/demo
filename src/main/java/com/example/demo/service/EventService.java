package com.example.demo.service;

import com.example.demo.entities.Event;

import java.util.List;

public interface EventService {
    Event save(Event event);
    boolean update(Event event);
    void delete(Long id);
    Event findById(Long id);
    List<Event> findAll();

    boolean checkTimeIsDuplicate(Long eventStart, Long eventEnd, List<Event> eventList);
}
