package com.example.reservapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reservapp.databinding.ActivityUserLogInBinding
import com.example.reservapp.databinding.ActivityWelcomeAppBinding

class UserLogInActivity : AppCompatActivity() {

    private  lateinit var userLoginBinding: ActivityUserLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userLoginBinding = ActivityUserLogInBinding.inflate(layoutInflater)
        val view = userLoginBinding.root
        setContentView(view)

        userLoginBinding.loginUserButton.setOnClickListener{
            val intent = Intent(this,BottomNavigationActivity::class.java)
            startActivity(intent)
        }

    }
}