package com.devtides.coroutinesroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devtides.coroutinesroom.model.LoginState
import com.devtides.coroutinesroom.model.User
import com.devtides.coroutinesroom.model.UserDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class SignupViewModel(application: Application) : AndroidViewModel(application) {

    val signupComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    private val db by lazy { UserDataBase(getApplication()).userDao() }

    fun signup(username: String, password: String, info: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = db.getUser(username)
            if(user != null) {
                error.postValue("User already exists")
                return@launch
            } else {
                val user = User(username, password.hashCode(), info)
                val userId = db.insertUser(user)
                user.id = userId
                LoginState.login(user)
                signupComplete.postValue(true)
            }
        }
    }

}