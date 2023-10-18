package com.example.reservapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserRegisterViewModel : ViewModel() {
    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun validateInputs(nameUser: String,
                       email: String,
                       phoneNumer: String,
                       password: String,
                       repasword: String
    ) {
        if(nameUser.isEmpty() || email.isEmpty() || phoneNumer.isEmpty() ||
            password.isEmpty() || repasword.isEmpty()) {
            errorMessage.value = "Ingrese los espacios requeridos"
        }
        else {
            if(password != repasword) {
                errorMessage.value = "Las contraseñas deben ser iguales"
            }
            else{
                if(password.length < 6) {
                    errorMessage.value = "Las contraseñas deben tener al menos 6 digitos"
                }
            }
        }

    }

}
