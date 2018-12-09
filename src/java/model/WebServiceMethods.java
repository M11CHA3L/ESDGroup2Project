/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author RyanCoole
 */
public class WebServiceMethods {
    
    public int calcDistance(String start, String end) throws MalformedURLException, IOException{
       
        final String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=";
        final String key = "AIzaSyAjXON-EKXSwBEk2bqnwj9Nc7nBPcYqD8I";
        
        URL u = new URL(url + start + "&destinations=" + end + "&departure_time=now&key=" + key);
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        ArrayList<String> list = new ArrayList<>();
        while ((inputLine = in.readLine()) != null) {
            list.add(inputLine);
        }
        in.close();

        String[] a = list.get(9).split(" ");
        double distance = Integer.parseInt(a[a.length - 1]) / 1609.344;

        int d = (int) Math.round(distance);

        return d;
    }
}

