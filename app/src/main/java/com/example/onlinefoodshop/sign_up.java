package com.example.onlinefoodshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class sign_up extends AppCompatActivity implements View.OnClickListener{

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

        mAuth = FirebaseAuth.getInstance();
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
        final String Name = name.getText().toString().trim();
        final String Address =address.getText().toString().trim();
        String City =city.getText().toString().trim();
        final String Mobile = mobile.getText().toString().trim();
        final String Email = email.getText().toString().trim();
        final String Username= username.getText().toString().trim();
        final String Password = password.getText().toString().trim();

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

        mAuth.createUserWithEmailAndPassword(Email,Password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        user user = new user(name,address,city,mobile,email,username,password);

                        FirebaseDatabase.getInstance().getReference("user")
                               .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){
                                    Toast.makeText(sign_up.this,"User Registered Successfully",Toast.LENGTH_LONG).show();
                                }else{
                                    System.out.println(Name);
                                    Toast.makeText(sign_up.this,"wrong password",Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                    }else {
                        Toast.makeText(sign_up.this,"Failed",Toast.LENGTH_LONG).show();
                    }
                }
            });



    }
}