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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    CallbackManager callbackManager;
    private TextView username, password, forgot_password;
    private Button buttonLogin, btnRegister;
    private ImageView fb_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        
        username =(TextView) findViewById(R.id.et_user_name);
        password =(TextView) findViewById(R.id.et_password);

        forgot_password =(TextView) findViewById(R.id.tv_forgot_password);
        forgot_password.setOnClickListener(this);

        buttonLogin =(Button) findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(this);

        btnRegister =(Button) findViewById(R.id.button_register);
        btnRegister.setOnClickListener(this);

        fb_button =(ImageView) findViewById(R.id.fb_btn);
        fb_button.setOnClickListener(this);

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, "Login successfull", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, SecondActivity.class);
                startActivity(intent);
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

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login:
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("1")){
                    Toast.makeText(LoginActivity.this, "Login successfull", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, SecondActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login False!!!", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.button_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.fb_btn:
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
                break;
        }
    }
}