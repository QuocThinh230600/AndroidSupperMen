package com.example.androidnangcao.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidnangcao.MainActivity;
import com.example.androidnangcao.R;


import com.example.androidnangcao.SecondActivity;
import com.example.androidnangcao.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private EditText et_register_name, et_register_email, et_register_password;
    private TextView tv_register_form;
    private Button button_back, button_register;
    private ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //thieeus nos debug lay vai l*
        mAuth = FirebaseAuth.getInstance();

        button_back =(Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(this);

        button_register =(Button) findViewById(R.id.button_register);
        button_register.setOnClickListener(this);

        tv_register_form =(TextView) findViewById(R.id.tv_register_form);
        tv_register_form.setOnClickListener(this);

        et_register_name =(EditText) findViewById(R.id.et_register_name);
        et_register_email =(EditText) findViewById(R.id.et_register_email);
        et_register_password =(EditText) findViewById(R.id.et_register_password);
//        et_register_password_2 =(EditText) findViewById(R.id.et_register_password_2);

        progress_bar =(ProgressBar) findViewById(R.id.progress_bar);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_register_form:
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.button_register:
                registerUser();
                break;

            case R.id.button_back:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    private void registerUser() {
        String username = et_register_name.getText().toString().trim();
        String email = et_register_email.getText().toString().trim();
        String password = et_register_password.getText().toString().trim();
//        String confirm_password = et_register_password_2.getText().toString().trim();



        if(username.isEmpty()){
            et_register_name.setError("User name is required!");
            et_register_name.requestFocus();
            return;
        }

        if(email.isEmpty()){
            et_register_email.setError("Email is required!");
            et_register_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_register_email.setError("Please provide valid email!");
            et_register_email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            et_register_password.setError("User name is required!");
            et_register_password.requestFocus();
            return;
        }

        if(password.length() < 6){
            et_register_password.setError("Min password length should be 6 character!");
            et_register_password.requestFocus();
            return;
        }

//        if(confirm_password.isEmpty()){
//            et_register_password_2.setError("Confirm password is required!");
//            et_register_password_2.requestFocus();
//            return;
//        }

        progress_bar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(username, email);

                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_SHORT).show();
                                        progress_bar.setVisibility(View.VISIBLE);
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Failed to registered! Try Again!", Toast.LENGTH_SHORT).show();
                                        progress_bar.setVisibility(View.GONE);
                                    }
                                }
                            });
                } else {
                    Toast.makeText(RegisterActivity.this, "Failed to registered! Try Again!", Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                }
            }
        });
    }
}