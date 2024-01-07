package com.example.cupproject.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DatabaseReference

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    class MainViewModelFactory(val repository: MainRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(repository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class :: ${modelClass::class.java.simpleName}")
        }
    }

    fun getUserInfo() {
        val info = repository.getUserInfo()
        Log.i(TAG, "$info")
    }

    fun setUserInfo() {
        val info = repository.setUserInfo("asd")
    }

    interface MainRepository {
        fun getUserInfo(): String
        fun setUserInfo(value: String)
    }

    class MainRepositoryImpl(private val database: DatabaseReference) : MainRepository {
        override fun getUserInfo(): String {
            var info = ""
            database.child("users").child("asdasdasd").get().addOnCompleteListener {
                info = it.result.value as String
            }.addOnFailureListener {
                Log.e(TAG, it.message, it)
            }
            return info
        }

        override fun setUserInfo(value: String) {
            database.child("users").child("asdasdasd").setValue("UserId")
        }
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}