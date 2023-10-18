package com.example.reservapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reservapp.databinding.ActivitySelectProfileBinding
import com.example.reservapp.databinding.ActivityWelcomeAppBinding

class SelectProfileActivity : AppCompatActivity() {

    private lateinit var selectProfileBinding: ActivitySelectProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectProfileBinding = ActivitySelectProfileBinding.inflate(layoutInflater)
        val view = selectProfileBinding.root
        setContentView(view)

        selectProfileBinding.goUserButton.setOnClickListener{
            val intent = Intent(this, UserRegisterActivity::class.java)
            startActivity(intent)
        }

        selectProfileBinding.goHostButton.setOnClickListener{
            val intent = Intent(this, HostRegisterActivity::class.java)
            startActivity(intent)
        }

    }
}