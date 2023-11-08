package com.example.reservapp.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reservapp.data.HostRepository
import com.example.reservapp.data.ResourceRemote

import com.example.reservapp.model.Host
import emailValidator
import kotlinx.coroutines.launch

class HostRegisterViewModel : ViewModel() {

    private val hostRepository = HostRepository()

    private val _errorMesg : MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMesg

    private val _registerSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val registerSuccess: LiveData<Boolean> = _registerSuccess


    fun validateFields(
        email: String,
        password: String,
        repPassword: String,
        phoneNumber: String,
        fullName: String
    ) {
        if(email.isEmpty() || password.isEmpty() || repPassword.isEmpty()) {
            _errorMesg.value = "Debe digitar todos los campos"
        } else {
            if (password != repPassword) {
                _errorMesg.value = "Las contraseñas deben ser iguales"
            } else {
                if(password.length < 6) {
                    _errorMesg.value = "Las contraseña debe tener mínimo 6 digitos"
                }
                else {
                    if(!emailValidator(email)) {
                        _errorMesg.value = "Correo electrónico invalido"
                    } else {

                        viewModelScope.launch {
                            val result = hostRepository.registerHost(email, password)
                            result.let { resouerceRemote ->
                                when (resouerceRemote) {
                                    is ResourceRemote.Success -> {
                                        var uid = result.data
                                        val host = Host(uid, email, phoneNumber, fullName)
                                        createHost(host)

                                    }
                                    is ResourceRemote.Error -> {
                                        var msg = result.message

                                        when(msg) {
                                            "The email address is already in use by another account." -> msg = "Ya existe una cuenta con este correo electrónico"
                                            "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg = "Revise su conexión de Internet"
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

    private fun createHost(host: Host) {
        viewModelScope.launch {
            val result = hostRepository.createHost(host)
            result.let { resouerceRemote ->
                when(resouerceRemote) {
                    is ResourceRemote.Success -> {
                        _registerSuccess.postValue(true)
                        _errorMesg.postValue("Host creado exitosamente")
                    }
                    is ResourceRemote.Error -> {
                        var msg = result.message
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
