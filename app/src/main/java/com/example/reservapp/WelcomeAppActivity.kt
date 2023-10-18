package com.example.reservapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reservapp.databinding.ActivityWelcomeAppBinding

class WelcomeAppActivity : AppCompatActivity() {

    private lateinit var welcomeapbBinding: ActivityWelcomeAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(3000)
        setTheme(R.style.Theme_ReservApp_NoActionBar)
        super.onCreate(savedInstanceState)
        welcomeapbBinding = ActivityWelcomeAppBinding.inflate(layoutInflater)
        val view = welcomeapbBinding.root
        setContentView(view)

        welcomeapbBinding.mainBeginButton.setOnClickListener{
            val intent = Intent(this, SelectProfileActivity::class.java)
            startActivity(intent)
        }

    }
}