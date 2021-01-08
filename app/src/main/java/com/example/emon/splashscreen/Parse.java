package com.example.emon.splashscreen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Emon on 8/8/2017.
 */

public class Parse {
    public static node parse(String content){
        try {
            JSONArray jsonArray = new JSONArray(content);

            JSONObject object = jsonArray.getJSONObject(0);

            node newNode = new node();
            newNode.setName(object.getString("name"));
            newNode.setSensor_id(object.getString("sensor_id"));
            newNode.setAvergae(object.getDouble("average"));

            return newNode;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
