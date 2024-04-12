package com.example.events;
//import java.util.String;
public class EventManager {
    private Event event;
    public EventManager(Event event){
        this.event = event;
    }
    public boolean enterEvent(String tagID){

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
    public boolean exitEvent(String tagID){
        event.removeAttendee(tagID);
        return true;
    }

    public boolean addBooking(String tagID){

        return event.addBooking(tagID);
    }
}
