package com.example;

import com.example.logic.Logic;
import com.example.logic.Req;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws JSONException
    {

        Logic logic_layer;
        logic_layer = new Logic();

        // Incoming requests:
        // booking request:
        JSONObject booking_details = new JSONObject();
        booking_details.put("tagID","user1");
        booking_details.put("eventID","Event1");
        Req booking_request = new Req("AddBooking",booking_details);
        System.out.println(logic_layer.handleRequest(booking_request));



    }
}
