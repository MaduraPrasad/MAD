package com.example.onlinefoodshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class sign_up extends AppCompatActivity implements View.OnClickListener {

    private TextView sign_up_btn_2;
    private TextView name, address, city, mobile, email, username, password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        sign_up_btn_2 = (Button) findViewById(R.id.sign_up_btn_2);
        sign_up_btn_2.setOnClickListener(this);

        name = (EditText) findViewById(R.id.input_name);
        address = (EditText) findViewById(R.id.input_address);
        city = (EditText) findViewById(R.id.input_city);
        mobile = (EditText) findViewById(R.id.input_mobile);
        email = (EditText) findViewById(R.id.input_email);
        username = (EditText) findViewById(R.id.input_user);
        password = (EditText) findViewById(R.id.input_user_psw);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_btn_2:
                sign_up_btn_2();
                break;
        }
    }

    private void sign_up_btn_2() {
        String Name = name.getText().toString().trim();
        String Address =address.getText().toString().trim();
        String City =city.getText().toString().trim();
        String Mobile = mobile.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Username= username.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if(Name.isEmpty()){
            name.setError("Name is Required");
            name.requestFocus();
            return;
        }
        if(Address.isEmpty()){
            address.setError("Address is Required");
            address.requestFocus();
            return;
        }
        if (Email.isEmpty()){
            email.setError("Email is Required");
            email.requestFocus();
            return;
        }
        if (Mobile.isEmpty()){
            mobile.setError("Mobile is Required");
            mobile.requestFocus();
            return;
        }
        if (City.isEmpty()){
            city.setError("City is Required");
            city.requestFocus();
            return;
        }
        if (Password.isEmpty()){
            password.setError("Password is Required");
            password.requestFocus();
            return;
        }
        if (Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }

        if (Password.length() < 6){
            password.setError("6 characters required");
            password.requestFocus();
            return;
        }
    }
}