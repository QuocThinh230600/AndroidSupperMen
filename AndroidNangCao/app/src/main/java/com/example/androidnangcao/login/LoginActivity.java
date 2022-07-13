package com.example.androidnangcao.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidnangcao.MainActivity;
import com.example.androidnangcao.SecondActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.example.androidnangcao.R;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        
        TextView username =(TextView) findViewById(R.id.et_user_name);
        TextView password =(TextView) findViewById(R.id.et_password);
        TextView forgot_password =(TextView) findViewById(R.id.tv_forgot_password);
        Button buttonLogin =(Button) findViewById(R.id.button_login);
        ImageView fb_button =(ImageView) findViewById(R.id.fb_btn);

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                startActivity(new Intent(LoginActivity.this, SecondActivity.class));
                finish();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(username.getText().toString().equals("admin") && password.getText().toString().equals("1")){
                     Toast.makeText(LoginActivity.this, "Login successfull", Toast.LENGTH_LONG).show();
                     startActivity(new Intent(LoginActivity.this, SecondActivity.class));
                     finish();
                 } else {
                    Toast.makeText(LoginActivity.this, "Login False!!!", Toast.LENGTH_LONG).show();
                 }
            }
        });

        fb_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}