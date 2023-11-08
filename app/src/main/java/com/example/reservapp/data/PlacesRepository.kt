package com.example.reservapp.data

import android.util.Log
import com.example.reservapp.model.Place
import com.example.reservapp.model.User
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class PlacesRepository {

    private var db = Firebase.firestore

    suspend fun createPlace(place: Place): ResourceRemote<String?> {
        return try {
            val document = db.collection("places").document()
            place.id = document.id
            db.collection("places").document(document.id).set(place).await()
            ResourceRemote.Success(data = document.id)
        }  catch (e: FirebaseFirestoreException) {
            Log.e("FirebaseFirestoreException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            Log.e("FirebaseNetworkException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseException) {
            Log.e("FirebaseException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun loadPlaces() : ResourceRemote<QuerySnapshot?> {
        return try {
            val result = db.collection("places").get()?.await()
            ResourceRemote.Success(data = result)
        }  catch (e: FirebaseFirestoreException) {
            Log.e("FirebaseFirestoreException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            Log.e("FirebaseNetworkException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseException) {
            Log.e("FirebaseException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }


}