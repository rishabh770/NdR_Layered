package com.example.logic;
import java.util.HashMap;
import java.util.UUID;
import com.example.events.EventManager;
import com.example.events.Event;

public class Logic {
    HashMap<UUID,EventManager> eventManagers;

    public Logic(){
        eventManagers = new HashMap<UUID,EventManager>();
        Event event = new Event("Event1","technical",10);
        EventManager eventManager = new EventManager(event);
        eventManagers.put(event.getID(),eventManager);
    }

}
