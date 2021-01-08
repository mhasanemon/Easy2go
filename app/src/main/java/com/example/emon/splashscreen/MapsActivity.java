package com.example.emon.splashscreen;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import static com.example.emon.splashscreen.Parse.parse;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public static final String JSON_URL = "http://prepmate.primepos.systems/traffic/index.php?id=1";
    node node1;
    Double average;

    private GoogleMap mMap;
    String start_address;
    String end_address;
    String distance;
    String duration;
    Double sourceLat;
    Double sourceLng;
    Double destinationLat;
    Double destinationLng;

    LatLng source,destination;

    TextView distanceView;
    TextView durationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        volleyStringRequest();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        start_address = getIntent().getStringExtra("start_address");
        end_address = getIntent().getStringExtra("end_address");
        distance = getIntent().getStringExtra("distance");
        duration = getIntent().getStringExtra("duration");
        sourceLat = getIntent().getDoubleExtra("sourceLat",0.0);
        sourceLng = getIntent().getDoubleExtra("sourceLng",0.0);
        destinationLat = getIntent().getDoubleExtra("destinationLat",0.0);
        destinationLng = getIntent().getDoubleExtra("destinationLng",0.0);

        source = new LatLng(sourceLat,sourceLng);
        destination = new LatLng(destinationLat,destinationLng);

        distanceView = (TextView) findViewById(R.id.distance);
        distanceView.setText("Distance : "+distance);

        durationView = (TextView) findViewById(R.id.duration);
        durationView.setText("Duration : "+duration);


    }

    private void volleyStringRequest(){

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("Result", response);
                        node1 = parse(response);
                        //textView.setText(node1.getName()+node1.getSensor_id()+node1.getAvergae());
                        //Toast.makeText(getApplicationContext(),node1.getName()+node1.getSensor_id()+node1.getAvergae(),Toast.LENGTH_SHORT).show();
                        average = node1.getAvergae();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        /*mMap.addMarker(new MarkerOptions().position(source).title(start_address));
        mMap.addMarker(new MarkerOptions().position(destination).title(end_address));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(source,17));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination,17));*/

        mMap.addMarker(new MarkerOptions().position(new LatLng(23.713712, 90.411501)).title("Ray Shaheb Bazaar"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(23.709892, 90.411910)).title("Jagannath University"));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(23.713712, 90.411501),17));


        if (average>0.0 && average<500.0){
            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(23.713712, 90.411501), new LatLng(23.709892, 90.411910))
                    .width(25)
                    .color(Color.GREEN));
        }

        else if (average>500.0 && average<1000.0){
            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(23.713712, 90.411501), new LatLng(23.709892, 90.411910))
                    .width(25)
                    .color(Color.YELLOW));
        }

        else if (average>1000.0 && average<1500.0){
            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(23.713712, 90.411501), new LatLng(23.709892, 90.411910))
                    .width(25)
                    .color(Color.RED));
        }
        //Toast.makeText(getApplicationContext(),Double.toString(average),Toast.LENGTH_SHORT).show();

    }
}
