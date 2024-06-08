package com.example.trip_planningproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
    private TextView welcome_message;

    private TextInputLayout emailInput , passwordInput;
    private TextInputEditText emailEditText , passwordEditText;

    private Button btnForget , btnLoginProcess , btnNewUser;

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
        passwordEditText = findViewById(R.id.passwordEditText);
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
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginPage.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, password);
                }
            }
        });
    }

    private void loginUser(String email, String password) {
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
}