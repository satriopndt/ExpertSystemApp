package com.satriopndt.expertsystem.ui.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.satriopndt.expertsystem.data.model.FakeDataResearchPost
import com.satriopndt.expertsystem.data.model.UserModel
import com.satriopndt.expertsystem.data.repository.SystemRepository
import com.satriopndt.expertsystem.ui.common.UiState
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: SystemRepository) : ViewModel() {
    var userId by mutableStateOf("")
    var password by mutableStateOf("")

    private val _login = MutableLiveData<UiState<UserModel>>()
    val login: LiveData<UiState<UserModel>> = _login

//    fun login(userId: String, password: String) {
//        viewModelScope.launch {
//            repository.
//        }
//    }

    fun saveSession(userModel: UserModel) {
        viewModelScope.launch {
            repository.saveSession(userModel)
        }
    }
}