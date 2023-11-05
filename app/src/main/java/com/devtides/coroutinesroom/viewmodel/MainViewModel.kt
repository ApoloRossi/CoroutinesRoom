package com.devtides.coroutinesroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devtides.coroutinesroom.model.LoginState
import com.devtides.coroutinesroom.model.UserDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {

    val userDeleted = MutableLiveData<Boolean>()
    val signout = MutableLiveData<Boolean>()
    private val db by lazy {
        UserDataBase(getApplication()).userDao()
    }

    fun onSignout() {
        LoginState.logout()
        signout.value = true
    }

    fun onDeleteUser() {
        viewModelScope.launch(Dispatchers.IO) {
            LoginState.user.let { user ->
                user?.let {
                    db.deleteUser(user.id)
                }
            }
            LoginState.logout()
            userDeleted.postValue(true)
        }
    }

}