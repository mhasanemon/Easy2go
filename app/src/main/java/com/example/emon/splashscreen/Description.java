package com.example.emon.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Description extends AppCompatActivity {
    TextView description;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        continueButton = (Button) findViewById(R.id.continueTo);
        description = (TextView) findViewById(R.id.textView2);

        description.setText("Hello!\nWelcome to a real time traffic.\nNowadays, controlling the traffic becomes major issue because of rapid increase in automobiles. So, in order to rectify this problem, we will go for density based traffic lights system.");

    }

    public void Continue(View view){
        Intent intent = new Intent(Description.this,MapsMainActivity.class);
        startActivity(intent);
    }
}
