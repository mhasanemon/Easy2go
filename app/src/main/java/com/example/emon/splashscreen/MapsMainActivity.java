package com.example.emon.splashscreen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.Serializable;
import java.util.List;

public class MapsMainActivity extends AppCompatActivity {
    EditText source;
    EditText destination;
    Button button;
    String API_KEY = "AIzaSyCVmWnQG2M6lkpSJVO18wTWKR9Yo_5PXbk";

    String BASE_ADDRESS ;
    String Source ="";
    String Destination="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_main);

        source = (EditText) findViewById(R.id.editText);
        destination = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);

        Source = source.getText().toString();
        Destination = destination.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Source = source.getText().toString();
                Destination = destination.getText().toString();

                BASE_ADDRESS =  "https://maps.googleapis.com/maps/api/directions/json?origin="+Source+"&destination="+Destination+"&"+API_KEY;
                sendRequest();

            }
        });
    }

    private void sendRequest(){

        StringRequest stringRequest = new StringRequest(BASE_ADDRESS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MapsMainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json)  {
        Direction_attributes attributes;

        ParseJSON parseJSON = new ParseJSON();
        attributes = parseJSON.parse(json);
        Double sourceLat = attributes.getSource().latitude;
        Double sourceLng = attributes.getSource().longitude;
        Double destinationLat = attributes.getDestination().latitude;
        Double destinationLng = attributes.getDestination().longitude;

        //Toast.makeText(this,attributes.getDistance()+"\n"+attributes.getDuration()+"\n"+attributes.getStart_address()+"\n"+attributes.getEnd_address()+"\n"+attributes.getSource()+"\n"+attributes.getDestination(),Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
        intent.putExtra("distance",attributes.getDistance());
        intent.putExtra("duration",attributes.getDuration());
        intent.putExtra("start_address",attributes.getStart_address());
        intent.putExtra("end_address",attributes.getEnd_address());
        intent.putExtra("sourceLat",sourceLat);
        intent.putExtra("sourceLng",sourceLng);
        intent.putExtra("destinationLat",destinationLat);
        intent.putExtra("destinationLng",destinationLng);

        startActivity(intent);

    }
}
