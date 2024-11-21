package com.satriopndt.expertsystem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.satriopndt.expertsystem.data.repository.SystemRepository
import com.satriopndt.expertsystem.ui.screen.home.HomeViewModel
import com.satriopndt.expertsystem.ui.screen.login.LoginViewModel

class ViewModelFactory(private val repository: SystemRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: "+ modelClass.name)
    }
}