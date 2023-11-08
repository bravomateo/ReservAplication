package com.example.reservapp.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reservapp.databinding.ActivitySelectProfileBinding
import com.example.reservapp.register.HostRegisterActivity
import com.example.reservapp.register.UserRegisterActivity

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