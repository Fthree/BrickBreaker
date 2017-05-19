package com.engine.Events;

import com.engine.Events.Type.IEvent;

import java.util.List;

public class EventSystem {
    private EventSystem(){}

    private static EventSystem eventSystem = new EventSystem();

    public EventSystem getEventSystem() { return eventSystem; }

    public void addToQueue(IEvent event) {
        primaryQueue.getEvents().add(event);
    }

    public List<IEvent> readQueue() {
        return primaryQueue.getEvents();
    }

    public void clearQueue() {
        primaryQueue.getEvents().clear();
    }

    EventQueue primaryQueue;
}
