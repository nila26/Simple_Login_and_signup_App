package com.example.kolkatadev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText txtEmail,txtname,txtphn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEmail =findViewById(R.id.Email);
        txtname=findViewById(R.id.name);
        txtphn=findViewById(R.id.phn);
        showalluserdata();
    }

    private void showalluserdata() {
        Intent intent=getIntent();
        String user_name=intent.getStringExtra("name");
        String user_phn=intent.getStringExtra("phone");
        String user_email=intent.getStringExtra("email");

        txtEmail.setText(user_email);
        txtname.setText(user_name);
        txtphn.setText(user_phn);
    }
}
