package com.example.kolkatadev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Sign_up extends AppCompatActivity {
    TextInputEditText txtEmail,txtPassword,txtConfirmPassword,txtname,txtphn;
    Button btn_register;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Sign Up Form");

        txtEmail =findViewById(R.id.email);
        txtname=findViewById(R.id.name);
        txtphn=findViewById(R.id.phn);
        txtPassword= findViewById(R.id.pwd);
        txtConfirmPassword= findViewById(R.id.cpwd);
        btn_register= findViewById(R.id.button);

        firebaseAuth=FirebaseAuth.getInstance();
        mydatabase=FirebaseDatabase.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = txtEmail.getText().toString().trim();
                String password=txtPassword.getText().toString().trim();
                final String name=txtname.getText().toString().trim();
                String phn=txtphn.getText().toString().trim();
                String confirmPassword=txtConfirmPassword.getText().toString().trim();
                final firebase_data fd= new firebase_data(name,phn,password,email,confirmPassword);

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Sign_up.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(Sign_up.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phn)){
                    Toast.makeText(Sign_up.this, "Please Enter Phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Sign_up.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(Sign_up.this, "Please Enter confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(Sign_up.this, "Password too short", Toast.LENGTH_SHORT).show();
                }
                if(password.equals(confirmPassword)){
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Sign_up.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        mydatabase.getReference("user").child(Objects.requireNonNull(firebaseAuth.getUid())).setValue(fd);
                                        Toast.makeText(Sign_up.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),Login_Form.class));
                                    } else {
                                        Toast.makeText(Sign_up.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });

                }

            }
        });
    }


}
