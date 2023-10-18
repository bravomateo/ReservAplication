package com.example.reservapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reservapp.databinding.ActivityHostLoginBinding
import com.example.reservapp.databinding.ActivityUserLogInBinding
import com.example.reservapp.ui.main.TabbedMainActivity

class HostLoginActivity : AppCompatActivity() {

    private  lateinit var hostLoginBinding: ActivityHostLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hostLoginBinding = ActivityHostLoginBinding.inflate(layoutInflater)
        val view = hostLoginBinding.root
        setContentView(view)

        hostLoginBinding.loginHostButton.setOnClickListener{
            val intent = Intent(this, TabbedMainActivity::class.java)
            startActivity(intent)
        }

    }


}