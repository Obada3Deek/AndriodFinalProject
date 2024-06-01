package com.example.trip_planningproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    private ImageView logo;
    private TextView welcome_message;
    private Button btnForget , btnLoginProcess , btnNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        btnForget = findViewById(R.id.btnForget);
        btnLoginProcess = findViewById(R.id.btnLogin);
        btnNewUser = findViewById(R.id.btnNewUser);
        logo = findViewById(R.id.imageLogo);
        welcome_message = findViewById(R.id.welcomeMessageLogin);

        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SignUpIntent = new Intent(LoginPage.this , SignUpClient.class);
                startActivity(SignUpIntent);
            }
        });

        btnLoginProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextAct = new Intent(LoginPage.this , UserProfile.class);
                startActivity(nextAct);
            }
        });
    }
}