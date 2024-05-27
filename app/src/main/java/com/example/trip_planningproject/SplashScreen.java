package com.example.trip_planningproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Handler;

public class SplashScreen extends AppCompatActivity {

    private static int SplachScreen = 5000;
    private Animation topAnim , buttomAnim;

    private ImageView imagelogo;

    private TextView textWelcome;
    private Button btnStartSplash;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imagelogo = findViewById(R.id.imageView2);
        textWelcome = findViewById(R.id.textViewWelcome);
        btnStartSplash = findViewById(R.id.buttonSplash);
        topAnim = AnimationUtils.loadAnimation(this ,R.anim.top_animation);
        buttomAnim = AnimationUtils.loadAnimation(this ,R.anim.buttom_animation);

        // Animation
        imagelogo.setAnimation(topAnim);
        textWelcome.setAnimation(buttomAnim);
        btnStartSplash.setAnimation(buttomAnim);

        btnStartSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(SplashScreen.this , MainActivity.class);
                startActivity(nextIntent);
                Toast.makeText(getApplicationContext(), "WELCOME!", Toast.LENGTH_SHORT).show();
            }
        });




    }
}