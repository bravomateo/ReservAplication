package com.example.reservapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.reservapp.BottomNavigationActivity
import com.example.reservapp.databinding.ActivityUserLogInBinding
import com.example.reservapp.register.UserRegisterViewModel

class UserLogInActivity : AppCompatActivity() {

    private lateinit var userLoginBinding: ActivityUserLogInBinding
    private lateinit var  userLogInViewModel: UserLogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userLoginBinding = ActivityUserLogInBinding.inflate(layoutInflater)
        userLogInViewModel = ViewModelProvider(this)[UserLogInViewModel::class.java]
        val view = userLoginBinding.root
        setContentView(view)

        userLogInViewModel.errorMsg.observe(this) {msg->
            showErrorMsg(msg)
        }

        userLogInViewModel.registerSuccess.observe(this){
            val intent = Intent(this, BottomNavigationActivity::class.java)
            startActivity(intent)
            finish()
        }

        userLoginBinding.loginUserButton.setOnClickListener {
            val email: String = userLoginBinding.emailLoginUserEditText.text.toString()
            val password = userLoginBinding.passwordLoginUserEditText.text.toString()

            userLogInViewModel.validateFields(email, password)
        }

    }

    private fun showErrorMsg(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}