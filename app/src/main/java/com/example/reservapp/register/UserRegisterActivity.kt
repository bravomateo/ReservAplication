package com.example.reservapp.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.reservapp.databinding.ActivityUserRegisterBinding
import com.example.reservapp.login.UserLogInActivity


class UserRegisterActivity : AppCompatActivity() {

    private  lateinit var userRegisterBinding: ActivityUserRegisterBinding

    // Declarar el ViewModel con el de UserRegisterViewModel
    private lateinit var userRegisterViewModel: UserRegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userRegisterBinding = ActivityUserRegisterBinding.inflate(layoutInflater)
        val view = userRegisterBinding.root
        setContentView(view)

        userRegisterViewModel = ViewModelProvider(this)[UserRegisterViewModel::class.java]

        userRegisterViewModel.errorMsg.observe(this) {msg->
            showErrorMsg(msg)
        }

        userRegisterViewModel.registerSuccess.observe(this){
            val intent = Intent(this, UserLogInActivity::class.java)
            startActivity(intent)
        }

        userRegisterBinding.registerUserButton.setOnClickListener{

            val email: String = userRegisterBinding.emailUserEditText.text.toString()
            val password = userRegisterBinding.passwordUserEditText.text.toString()
            val repPassword = userRegisterBinding.repasswordUserEditText.text.toString()
            val phoneNumber = userRegisterBinding.phoneUserEditText.text.toString()
            val fullName = userRegisterBinding.nameUserEditText.text.toString()

            userRegisterViewModel.validateFields(email, password, repPassword, phoneNumber, fullName)

        }

        userRegisterBinding.logInUserTextView.setOnClickListener{
            val intent = Intent(this, UserLogInActivity::class.java)
            startActivity(intent)
        }


    }
    private fun showErrorMsg(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}