package com.example.trip_planningproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class SignUpClient extends AppCompatActivity {


    private TextInputLayout firstNameText, lastNameText, emailText, passwordText, phoneNumberText;
    private TextInputEditText firstNameEditText, lastNameEditText, emailEditText, passwordEditText, phoneNumberEditText;
    private RadioGroup genderRadioGroup;
    private RadioButton selectedGenderRadioButton;
    private Button signUpButton, backLoginButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_client);

        firstNameText = findViewById(R.id.firstNameText);
        lastNameText = findViewById(R.id.lastNameText);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        phoneNumberText = findViewById(R.id.phoneNumberText);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);

        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        signUpButton = findViewById(R.id.SIGNUPbtn);
        backLoginButton = findViewById(R.id.backLoginbtn);

        // Set click listener for the Sign Up button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString().trim();
                String gender = ""; // Initialize gender variable

                // Check if any radio button is selected
                if (genderRadioGroup.getCheckedRadioButtonId() != -1) {
                    gender = genderRadioGroup.getCheckedRadioButtonId() == R.id.maleRadioButton ? "Male" : "Female";
                }

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String phone = phoneNumberEditText.getText().toString().trim();

                if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(gender) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "PLEASE ENTER ALL INFORMATION", Toast.LENGTH_LONG).show();
                } else {
                    SharedPreferences register = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = register.edit();
                    // Store all information into SharedPreferences
                    editor.putString("FirstName", firstName);
                    editor.putString("LastName", lastName);
                    editor.putString("Gender", gender);
                    editor.putString("Email", email);
                    editor.putString("Password", password);
                    editor.putString("PhoneNumber", phone);
                   // editor.apply();
                    editor.commit();
                    Intent goLogin = new Intent(SignUpClient.this, LoginPage.class);
                    startActivity(goLogin);
                    finish();
                }
            }
        });


        // Set click listener for the Back to Login button
        backLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back to login action

            }
        });
    }

}