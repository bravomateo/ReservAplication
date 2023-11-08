package com.example.reservapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.reservapp.BottomNavigationActivity
import com.example.reservapp.databinding.ActivityHostLoginBinding
import com.example.reservapp.mainHost.MainActivityHost

class HostLoginActivity : AppCompatActivity() {

    private lateinit var hostLoginBinding: ActivityHostLoginBinding
    private lateinit var  hostLoginViewModel: HostLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hostLoginBinding = ActivityHostLoginBinding.inflate(layoutInflater)
        hostLoginViewModel = ViewModelProvider(this)[HostLoginViewModel::class.java]
        val view = hostLoginBinding.root
        setContentView(view)

        hostLoginViewModel.errorMsg.observe(this) {msg->
            showErrorMsg(msg)
        }

        hostLoginViewModel.registerSuccess.observe(this){
            val intent = Intent(this, MainActivityHost::class.java)
            startActivity(intent)
            finish()
        }

        hostLoginBinding.loginHostButton.setOnClickListener {
            val email: String = hostLoginBinding.emailLoginHostEditText.text.toString()
            val password = hostLoginBinding.passwordLoginHostEditText.text.toString()

            hostLoginViewModel.validateFields(email, password)
        }

    }

    private fun showErrorMsg(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}