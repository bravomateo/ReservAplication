package com.example.reservapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.reservapp.databinding.ActivityUserLogInBinding
import com.example.reservapp.databinding.ActivityUserRegisterBinding

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


        val errorMesaggeOberserver = Observer<String> { errorMesagge ->
            Toast.makeText(this, errorMesagge, Toast.LENGTH_LONG).show() }
        userRegisterViewModel.errorMessage.observe(this, errorMesaggeOberserver)

        userRegisterBinding.registerUserButton.setOnClickListener {
            userRegisterViewModel.validateInputs(
                userRegisterBinding.nameUserEditText.text.toString(),
                userRegisterBinding.emailUserEditText.text.toString(),
                userRegisterBinding.phoneUserEditText.text.toString(),
                userRegisterBinding.passwordUserEditText.text.toString(),
                userRegisterBinding.repasswordUserEditText.text.toString()

            )

            if (!userRegisterBinding.nameUserEditText.text.toString().isEmpty()  &&
                !userRegisterBinding.emailUserEditText.text.toString().isEmpty() &&
                !userRegisterBinding.phoneUserEditText.text.toString().isEmpty() &&
                !userRegisterBinding.passwordUserEditText.text.toString().isEmpty() &&
                !userRegisterBinding.repasswordUserEditText.text.toString().isEmpty()
            ) {
                val intent = Intent(this,BottomNavigationActivity::class.java)
                startActivity(intent)
            }

        }


        userRegisterBinding.logInUserTextView.setOnClickListener{
            val intent = Intent(this,UserLogInActivity::class.java)
            startActivity(intent)
        }

    }

}