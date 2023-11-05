package com.devtides.coroutinesroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devtides.coroutinesroom.model.LoginState
import com.devtides.coroutinesroom.model.UserDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val loginComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val db by lazy {
        UserDataBase(getApplication()).userDao()
    }

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            db.getUser(username)?.let {
                if(it.passwordHash == password.hashCode()) {
                    LoginState.login(it)
                    loginComplete.postValue(true)
                } else {
                    error.postValue("Incorrect password")
                }
            }?:run {
                error.postValue("User not found")
            }
        }
    }
}