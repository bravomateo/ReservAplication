package com.example.reservapp.mainHost.ui.newplace
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reservapp.data.PlacesRepository
import com.example.reservapp.data.ResourceRemote
import com.example.reservapp.model.Place
import com.example.reservapp.model.User
import kotlinx.coroutines.launch

class NewPlaceViewModel : ViewModel() {

    val placesRepository = PlacesRepository()

    private val _errorMesg : MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMesg

    private val _createPlaceSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val createPlaceSuccess: LiveData<Boolean> = _createPlaceSuccess

    fun validateFields(name: String, city: String, price: String, capacity: String) {
        if(name.isEmpty() || city.isEmpty() || price.isEmpty() || capacity.isEmpty()) {
            _errorMesg.value = "Debe llenar todos los campos"
        } else{
            val place = Place(name = name, city = city, price = price, capacity = capacity)
            viewModelScope.launch {

                val result = placesRepository.createPlace(place)
                result.let { resouerceRemote ->
                    when (resouerceRemote) {
                        is ResourceRemote.Success -> {
                            _errorMesg.postValue("Lugar guardado exitosamente")
                            _createPlaceSuccess.postValue(true)
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

}