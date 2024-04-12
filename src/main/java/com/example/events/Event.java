package com.example.events;
import java.util.UUID;
import java.util.List;

public class Event {
    UUID eventID;
    String eventName;
    String Location;
    String category;
    int MaxCapacity;
    int currentCapacity;
    List<UUID> bookings;
    List<UUID> attendees;

    public Event(String eventName, String category, int MaxCapacity){
        this.eventID = UUID.randomUUID();
        this.eventName = eventName;
        this.Location = "paris";
        this.category = category;
        this.MaxCapacity = MaxCapacity;
        this.currentCapacity = 0;
    }

    public boolean isBooked(UUID tagID){
        // check if the tagID is in the list of attendees
        return bookings.contains(tagID);
    }
    public boolean isSpaceAvailable(){
        // check if the current capacity is less than the max capacity
        return currentCapacity < MaxCapacity;
    }
    public void addAttendee(UUID tagID){
        // add the tagID to the list of attendees
        attendees.add(tagID);
        reduceVacancy();
    }
    public void removeAttendee(UUID tagID){
        attendees.remove(tagID);
        if(isBooked(tagID)){
            removeBooking(tagID);
        }
        currentCapacity = currentCapacity - 1;
    }
    public void reduceVacancy(){
        currentCapacity = currentCapacity + 1;
    }
    public boolean addBooking(UUID tagID){
        if(isSpaceAvailable()){
            bookings.add(tagID);
            reduceVacancy();
            return true;
        }
        else{
            return false;
        }
    }
    public void removeBooking(UUID tagID){

        bookings.remove(tagID);
    }

    // Getters and Setters
    public UUID getID(){
        return eventID;
    }
    public String getName(){
        return eventName;
    }
    public String getLocation(){
        return Location;
    }
    public String getCategory(){
        return category;
    }
    public int getMaxCapacity(){
        return MaxCapacity;
    }
    public int getCurrentCapacity(){
        return currentCapacity;
    }

}