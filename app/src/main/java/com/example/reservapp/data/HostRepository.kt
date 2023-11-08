package com.example.reservapp.data

import android.util.Log
import com.example.reservapp.model.Host
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class HostRepository {
    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore

    suspend fun registerHost(email: String, password: String): ResourceRemote<String?> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            ResourceRemote.Success(data = result.user?.uid)
        } catch (e: FirebaseAuthException) {
            Log.e("Register", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            Log.e("RegisterNetwork", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun loginHost(email: String, password: String): ResourceRemote<String?> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            ResourceRemote.Success(data = result.user?.uid)
        } catch (e: FirebaseAuthException) {
            Log.e("FirebaseAuthException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            Log.e("FirebaseNetworkException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseException) {
            Log.e("FirebaseException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun createHost(host: Host): ResourceRemote<String?>  {
        return try {
            host.uid?.let { db.collection("hosts").document(it).set(host).await() }
            ResourceRemote.Success(data = host.uid)
        } catch (e: FirebaseFirestoreException) {
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