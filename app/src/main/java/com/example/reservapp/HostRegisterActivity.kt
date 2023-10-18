package com.example.reservapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.reservapp.databinding.ActivityHostRegisterBinding
import com.example.reservapp.databinding.ActivityUserRegisterBinding
import com.example.reservapp.ui.main.TabbedMainActivity

class HostRegisterActivity : AppCompatActivity() {

    private  lateinit var hostRegisterBinding: ActivityHostRegisterBinding

    // Declarar el ViewModel con el de UserRegisterViewModel
    private lateinit var hostRegisterViewModel: HostRegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hostRegisterBinding = ActivityHostRegisterBinding.inflate(layoutInflater)
        val view = hostRegisterBinding.root
        setContentView(view)

        hostRegisterViewModel = ViewModelProvider(this)[HostRegisterViewModel::class.java]

        //val errorMesaggeOberserver = Observer<String> { errorMesagge ->
        //    Toast.makeText(this, errorMesagge, Toast.LENGTH_LONG).show() }
        //hostRegisterViewModel.errorMessage.observe(this, errorMesaggeOberserver)

        val errorMessageObserver = Observer<String> { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show() }
        hostRegisterViewModel.errorMessage.observe(this, errorMessageObserver)

        hostRegisterBinding.registerHostButton.setOnClickListener {
            if (!hostRegisterBinding.nameHostEditText.text.toString().isEmpty()  &&
                !hostRegisterBinding.emailHostEditText.text.toString().isEmpty() &&
                !hostRegisterBinding.phoneHostEditText.text.toString().isEmpty() &&
                !hostRegisterBinding.passwordHostEditText.text.toString().isEmpty() &&
                !hostRegisterBinding.repasswordHostEditText.text.toString().isEmpty()
            ) {
                val intent = Intent(this,TabbedMainActivity::class.java)
                startActivity(intent)
            }

        }


        hostRegisterBinding.logInHostTextView.setOnClickListener{
            val intent = Intent(this,HostLoginActivity::class.java)
            startActivity(intent)
        }

    }
}