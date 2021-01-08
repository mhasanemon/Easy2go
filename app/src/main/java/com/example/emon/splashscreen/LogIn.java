package com.example.emon.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogIn extends AppCompatActivity {
    Button without_logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        without_logIn = (Button) findViewById(R.id.without_login);
    }

    public void withoutLogIn(View view){
        Intent intent = new Intent(this,Description.class);
        startActivity(intent);

    }
}
