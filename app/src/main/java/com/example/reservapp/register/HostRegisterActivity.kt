package com.example.reservapp.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.reservapp.databinding.ActivityHostRegisterBinding
import com.example.reservapp.login.HostLoginActivity



class HostRegisterActivity : AppCompatActivity() {

    private  lateinit var hostRegisterBinding: ActivityHostRegisterBinding


    private lateinit var hostRegisterViewModel: HostRegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hostRegisterBinding = ActivityHostRegisterBinding.inflate(layoutInflater)
        val view = hostRegisterBinding.root
        setContentView(view)

        hostRegisterViewModel = ViewModelProvider(this)[HostRegisterViewModel::class.java]

        hostRegisterViewModel.errorMsg.observe(this) {msg->
            showErrorMsg(msg)
        }

        hostRegisterViewModel.registerSuccess.observe(this){
            val intent = Intent(this, HostLoginActivity::class.java)
            startActivity(intent)
        }

        hostRegisterBinding.registerHostButton.setOnClickListener{

            val email: String = hostRegisterBinding.emailHostEditText.text.toString()
            val password = hostRegisterBinding.passwordHostEditText.text.toString()
            val repPassword = hostRegisterBinding.repasswordHostEditText.text.toString()
            val phoneNumber = hostRegisterBinding.phoneHostEditText.text.toString()
            val fullName = hostRegisterBinding.nameHostEditText.text.toString()

            hostRegisterViewModel.validateFields(email, password, repPassword, phoneNumber, fullName)

        }

        hostRegisterBinding.logInHostTextView.setOnClickListener{
            val intent = Intent(this, HostLoginActivity::class.java)
            startActivity(intent)
        }


    }
    private fun showErrorMsg(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}