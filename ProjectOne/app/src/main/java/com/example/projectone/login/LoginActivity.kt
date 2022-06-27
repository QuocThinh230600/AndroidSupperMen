package com.example.projectone.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.projectone.MainActivity
import com.example.projectone.R
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<TextView>(R.id.et_user_name)
        val password = findViewById<TextView>(R.id.et_password)

        val buttonlogin = findViewById<Button>(R.id.button_login)

        buttonlogin.setOnClickListener{
            if(username.text.toString() == "admin" && password.text.toString() == "1"){
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}