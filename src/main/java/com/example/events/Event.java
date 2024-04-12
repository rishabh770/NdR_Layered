package com.example.events;
//import java.util.String;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private String eventID;
    private String eventName;
    private String Location;
    private String category;
    private int MaxCapacity;
    private int currentCapacity;
    private List<String> bookings;
    private List<String> attendees;

    public Event(String eventName, String category, int MaxCapacity){
        this.eventID = eventName;
        this.eventName = eventName;
        this.Location = "paris";
        this.category = category;
        this.MaxCapacity = MaxCapacity;
        this.currentCapacity = 0;
        bookings = new ArrayList<>();
        attendees = new ArrayList<>();
    }

    public boolean isBooked(String tagID){
        // check if the tagID is in the list of attendees
        return bookings.contains(tagID);
    }
    public boolean isSpaceAvailable(){
        // check if the current capacity is less than the max capacity
        return currentCapacity < MaxCapacity;
    }
    public void addAttendee(String tagID){
        // add the tagID to the list of attendees
        attendees.add(tagID);
        reduceVacancy();
    }
    public void removeAttendee(String tagID){
        attendees.remove(tagID);
        if(isBooked(tagID)){
            removeBooking(tagID);
        }
        currentCapacity = currentCapacity - 1;
    }
    public void reduceVacancy(){
        currentCapacity = currentCapacity + 1;
    }
    public boolean addBooking(String tagID){
        if(isSpaceAvailable()){
            bookings.add(tagID);
            reduceVacancy();
            return true;
        }
        else{
            return false;
        }
    }
    public void removeBooking(String tagID){

        bookings.remove(tagID);
    }

    // Getters and Setters
    public String getID(){
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