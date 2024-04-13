package com.example;

import com.example.logic.Logic;
import com.example.logic.Req;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class App
{
    private Logic logic_layer;

    public static void main( String[] args ) throws JSONException
    {

        App app = new App();
        app.logic_layer = new Logic();

        // Incoming requests:
        // booking request:
//        JSONObject booking_details = new JSONObject();
//        booking_details.put("tagID","user1");
//        booking_details.put("eventID","Event1");
//        Req booking_request = new Req("AddBooking",booking_details);
//        System.out.println(app.logic_layer.handleRequest(booking_request));

        app.run();

    }

    public void run() {

        ServerSocket ss = null;
        try {
            ss = new ServerSocket(8000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            Socket socket = null;
            try {
                socket = ss.accept();
                handleSocketRequest(socket);
//                ClientServiceThread cliThread = new ClientServiceThread(socket, this);
//                cliThread.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void handleSocketRequest(Socket s){
        BufferedReader in = null;
        OutputStream out = null;
        System.out.println("Received socket request ...");
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = s.getOutputStream();

            String req = in.readLine();

            if (req != null) {

                boolean status = handleReq(req);
//
                String message = status?"Success":"Failure";
//                String message = "Success";
                final String CRLF = "\r\n";

                String response =
                        "HTTP/1.1 200 OK" + CRLF +
                                "Content-Length: " + message.getBytes().length + CRLF +
                                CRLF +
                                message + CRLF + CRLF;

                out.write(response.getBytes());

            }
            System.out.println("Closing connection...");
            in.close();
            out.close();
            s.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean handleReq(String reqString){

        Req request = parseString(reqString);
        try{
            return logic_layer.handleRequest(request);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    private Req parseString(String string){
        String paramsString = string.split("\\?")[1];
        paramsString = paramsString.split("\\s+")[0];

        Map<String, String> queryParams = new HashMap<>();
        for (String param : paramsString.split("&")) {
            String[] keyValue = param.split("=");
            String key = keyValue[0];
            String value = keyValue.length > 1 ? keyValue[1] : "";
            queryParams.put(key, value);
        }

        JSONObject data = new JSONObject();
        try{
            data.put("tagID", queryParams.get("tagID"));
            data.put("eventID", queryParams.get("eventID"));
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        String type = queryParams.get("eventType");

        return new Req(type, data);

    }
}
