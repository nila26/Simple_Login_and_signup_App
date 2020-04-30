package com.example.kolkatadev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login_Form extends AppCompatActivity {
    TextInputEditText txtEmail,txtPassword;
    Button btn_login;
     FirebaseAuth firebaseAuth;
     FirebaseDatabase mydatabase;
     DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("Login Form");
        txtEmail =findViewById(R.id.mail);
        txtPassword=findViewById(R.id.pwd);
        btn_login=findViewById(R.id.login);
        firebaseAuth=FirebaseAuth.getInstance();
        mydatabase=FirebaseDatabase.getInstance();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Login_Form.this, "Succesfully logged in.",
                                                Toast.LENGTH_SHORT).show();
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
                                        String id=user.getUid();
                                        if (user != null) {
                                            // Name, email address, and profile photo Url
                                            //Toast.makeText(Login_Form.this, "user not null.",Toast.LENGTH_SHORT).show();
                                            isUser(id);


                                        }
                                    }else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Login_Form.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }


            }

            private void isUser(final String id) {
                //final String password = txtPassword.getText().toString().trim();
                // Read from the database
                myRef = mydatabase.getReference("user");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.child(id).child("phn").getValue(String.class);
                        //Toast.makeText(Login_Form.this, "value is"+value,Toast.LENGTH_SHORT).show();
                            String phoneDb = dataSnapshot.child(id).child("phn").getValue(String.class);
                            String emailDb = dataSnapshot.child(id).child("email").getValue(String.class);
                            String  nameDb= dataSnapshot.child(id).child("name").getValue(String.class);
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            intent.putExtra("name",nameDb);
                            intent.putExtra("phone",phoneDb);
                            intent.putExtra("email",emailDb);
                            startActivity(intent);

                        }
                    @Override
                    public void onCancelled(DatabaseError error) {

                    }

                });
            }
        });
    }
}