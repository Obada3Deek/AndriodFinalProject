package com.example.trip_planningproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUpClient extends AppCompatActivity {

    private Animation topAnim , buttomAnim;
    private ImageView logo;
    private TextView welcometxt , Destxt;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_client);
        btnLogin = findViewById(R.id.backLoginbtn);
        logo = findViewById(R.id.image_SignUp);
        welcometxt = findViewById(R.id.welcometxt);
        Destxt = findViewById(R.id.Destxt);

        topAnim = AnimationUtils.loadAnimation(this ,R.anim.top_animation);
        buttomAnim = AnimationUtils.loadAnimation(this ,R.anim.buttom_animation);

        logo.setAnimation(topAnim);
        welcometxt.setAnimation(topAnim);
        Destxt.setAnimation(topAnim);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginintent = new Intent(SignUpClient.this , LoginPage.class);
                startActivity(loginintent);
            }
        });
    }

    public void registerUserValidation(View view){
        String firstName;
        String lastName;
        String email;
        String phoneNumber;
        String password;
        String gender;

    }
}