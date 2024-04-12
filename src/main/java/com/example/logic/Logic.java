package com.example.logic;
import java.util.HashMap;
//import java.util.String;
import com.example.events.EventManager;
import com.example.events.Event;
import org.json.JSONException;
import org.json.JSONObject;


public class Logic {
    private HashMap<String,EventManager> eventManagers;

    public Logic(){
        eventManagers = new HashMap<>();
        addEvent("Event1","Technical",10);
        addEvent("Event2","Entertainment",5);
        addEvent("Event3","FoodStall",4);
    }
    private void addEvent(String name,String type, int capacity){
        Event event = new Event(name,type,capacity);
        EventManager eventManager = new EventManager(event);
        System.out.println("Event added: "+event.getID().toString()+" "+name+" "+type+" "+capacity);
        this.eventManagers.put(event.getID(),eventManager);
    }

    public boolean handleRequest(Req request) throws JSONException{
        switch (request.getType()) {
            case "Enter":
                return enterEvent(request.getData());
            case "Exit":
                return exitEvent(request.getData());
            case "AddBooking":
                return addBooking(request.getData());
            default:
                return false;
        }
    }

    private boolean enterEvent(JSONObject data) throws JSONException{
        String tagID = data.getString("tagID");
        String eventID = data.getString("eventID");
        EventManager eventManager = eventManagers.get(eventID);
        return eventManager.enterEvent(tagID);
    }
    private boolean exitEvent(JSONObject data) throws JSONException {
        String tagID = data.getString("tagID");
        String eventID = data.getString("eventID");
        EventManager eventManager = eventManagers.get(eventID);
        return eventManager.exitEvent(tagID);
    }
    private boolean addBooking(JSONObject data) throws JSONException{
        String tagID = data.getString("tagID");
        String eventID = data.getString("eventID");
        EventManager eventManager = eventManagers.get(eventID);
        return eventManager.addBooking(tagID);
    }

}
