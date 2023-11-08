package com.example.reservapp.mainHost.ui.myplaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reservapp.data.PlacesRepository
import com.example.reservapp.data.ResourceRemote

import com.example.reservapp.model.Place
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.launch

class MyPlacesViewModel : ViewModel() {

    private val placesRepository = PlacesRepository()

    private var placesListLocal = mutableListOf<Place?>()

    private val _errorMsg : MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _placesList: MutableLiveData<MutableList<Place?>> = MutableLiveData()
    val placesList: LiveData<MutableList<Place?>> = _placesList

    fun loadPlaces() {
        viewModelScope.launch {
            val result = placesRepository.loadPlaces()
            result.let { resourceRemote ->
                when(resourceRemote) {
                    is ResourceRemote.Success -> {
                        result.data?.documents?.forEach {  document ->
                            val place = document.toObject<Place>()
                            placesListLocal.add(place)
                        }
                        _placesList.postValue(placesListLocal)
                    }
                    is ResourceRemote.Error -> {
                        val msg = result.message
                        _errorMsg.postValue(msg)

                    }
                    else -> {
                        // don´t use
                    }
                }

            }
        }

    }

}