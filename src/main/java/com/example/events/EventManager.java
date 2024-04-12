package com.example.events;
import java.util.UUID;
public class EventManager {
    Event event;
    public EventManager(Event event){
        this.event = event;
    }
    public boolean enterEvent(UUID tagID){

        if(event.isBooked(tagID)){
            event.addAttendee(tagID);
            return true;
        }
        else if(event.isSpaceAvailable()){
            event.addAttendee(tagID);
            return true;
        }
        else {
            return false;
        }
    }
    public boolean exitEvent(UUID tagID){
        event.removeAttendee(tagID);
        return true;
    }

    public boolean addBooking(UUID tagID){

        return event.addBooking(tagID);
    }
}
