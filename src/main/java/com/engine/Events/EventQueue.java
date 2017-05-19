package com.engine.Events;

import com.engine.Events.Type.IEvent;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EventQueue {
    public List<IEvent> events = new ArrayList<>();
}
