package com.example.emon.splashscreen;

/**
 * Created by Emon on 7/26/2017.
 */

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ParseJSON {
    private String distance;
    private String duration;
    private String start_address;
    private String end_address;
    private LatLng source;
    private LatLng destination;

    public Direction_attributes parse(String content) {
        JSONObject jsonObject=null;
        JSONArray routes = null;
        try {
            jsonObject = new JSONObject(content);

            // routesArray contains ALL routes
            JSONArray routesArray = jsonObject.getJSONArray("routes");
            // Grab the first route
            JSONObject route = routesArray.getJSONObject(0);
            // Take all legs from the route
            JSONArray legs = route.getJSONArray("legs");
            // Grab first leg
            JSONObject leg = legs.getJSONObject(0);

            JSONObject durationObject = leg.getJSONObject("duration");
            duration = durationObject.getString("text");

            JSONObject distanceObject = leg.getJSONObject("distance");
            distance = distanceObject.getString("text");

            start_address = leg.getString("start_address");
            end_address = leg.getString("end_address");

            JSONObject startLocationObject = leg.getJSONObject("start_location");
            Double lat = startLocationObject.getDouble("lat");
            Double lng = startLocationObject.getDouble("lng");
            source = new LatLng(lat,lng);

            JSONObject endLocationObject = leg.getJSONObject("end_location");
            Double lat2 = endLocationObject.getDouble("lat");
            Double lng2 = endLocationObject.getDouble("lng");
            destination = new LatLng(lat2,lng2);

            Direction_attributes attributes = new Direction_attributes();
            attributes.setDistance(distance);
            attributes.setDuration(duration);
            attributes.setStart_address(start_address);
            attributes.setEnd_address(end_address);
            attributes.setSource(source);
            attributes.setDestination(destination);

            return attributes;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}