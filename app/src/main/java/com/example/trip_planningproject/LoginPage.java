package com.example.trip_planningproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {

    private ImageView logo;
    private Animation topAnim , buttomAnim;
    private TextView welcome_message , test;

    private TextInputLayout emailInput , passwordInput;
    private TextInputEditText emailEditText , passwordEditText;

    private Button btnForget , btnLoginProcess , btnNewUser;
    private CheckBox rememberMeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        btnForget = findViewById(R.id.btnForget);
        btnLoginProcess = findViewById(R.id.btnLogin);
        btnNewUser = findViewById(R.id.btnNewUser);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        emailEditText = findViewById(R.id.emailEditText);
        //test = findViewById(R.id.test111);
        passwordEditText = findViewById(R.id.passwordEditText);
        rememberMeCheckBox = findViewById(R.id.checkBoxRememberMe);
        logo = findViewById(R.id.imageLogo);
        welcome_message = findViewById(R.id.welcomeMessageLogin);
        SharedPreferences register = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String emailSH = register.getString("Email", "");
        String passwordSH = register.getString("Password", "");
       // test.setText("Email = "+emailSH+"\nPass ="+passwordSH);
        topAnim = AnimationUtils.loadAnimation(this ,R.anim.top_animation);
        buttomAnim = AnimationUtils.loadAnimation(this ,R.anim.buttom_animation);

        logo.setAnimation(topAnim);
        welcome_message.setAnimation(topAnim);
        btnLoginProcess.setAnimation(buttomAnim);
        btnNewUser.setAnimation(buttomAnim);

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
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Check if email or password fields are empty
                boolean areFieldsEmpty = TextUtils.isEmpty(email) || TextUtils.isEmpty(password);
                if (areFieldsEmpty) {
                    Toast.makeText(LoginPage.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if fields are empty
                }

// Check if email and password match the stored values
                boolean isLoginValid = email.equals(emailSH) && password.equals(passwordSH);
                if (!isLoginValid) {
                    Toast.makeText(LoginPage.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if login is invalid
                }

// Save "Remember Me" preference if checkbox is checked
                if (rememberMeCheckBox.isChecked()) {
                    SharedPreferences.Editor editor = register.edit();
                    editor.putBoolean("rememberMe", true);
                    editor.apply();
                }

// Start the MainActivity and finish the LoginPage activity
                Intent intent = new Intent(LoginPage.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
                    //  loginUser(email, password);


        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "WELCOME BACK", Toast.LENGTH_LONG).show();
    }
}

   /*  private void loginUser(String email, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2/carRental/login.php"; // Use 10.0.2.2 to access localhost from Android emulator

       // String url = "http://localhost/carRental/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean error = jsonObject.getBoolean("error");
                            String message = jsonObject.getString("message");

                            Toast.makeText(LoginPage.this, message, Toast.LENGTH_SHORT).show();
                            if (!error) {
                                // Proceed to the next activity or do other necessary tasks
                                Intent gonext = new Intent(LoginPage.this, UserProfile.class);
                                startActivity(gonext);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                           // Toast.makeText(LoginPage.this, "JSON error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginPage.this, "Volley error", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    */

