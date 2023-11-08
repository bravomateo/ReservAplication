package com.example.reservapp.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reservapp.data.ResourceRemote
import com.example.reservapp.data.UserRepository
import emailValidator
import kotlinx.coroutines.launch

class UserLogInViewModel: ViewModel() {

    private val userRepository = UserRepository()

    private val _errorMesg : MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMesg

    private val _registerSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val registerSuccess: LiveData<Boolean> = _registerSuccess

    fun validateFields(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()) {
            _errorMesg.value = "Debe digitar todos los campos"
        }
        else {
            if(password.length < 6) {
                _errorMesg.value = "Las contraseña debe tener mínimo 6 digitos"
            }
            else {
                if(!emailValidator(email)) {
                    _errorMesg.value = "Correo electrónico invalido"
                }
                else {
                    viewModelScope.launch {
                        val result = userRepository.loginUser(email, password)
                        result.let { resouerceRemote ->
                            when (resouerceRemote) {
                                is ResourceRemote.Success -> {
                                    _registerSuccess.postValue(true)
                                    _errorMesg.postValue("Bienvenido")
                                }
                                is ResourceRemote.Error -> {
                                    var msg = result.message

                                    when(msg) {
                                        "The email address is already in use by another account." -> msg = "Ya existe una cuenta con este correo electrónico"
                                        "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg = "Revise su conexión de Internet"
                                        "An internal error has occurred. [ INVALID_LOGIN_CREDENTIALS ]" -> msg = "Correo eletronico o contraseña invalida"
                                    }
                                    _errorMesg.postValue(msg)
                                }
                                else -> {
                                    // don't use
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}