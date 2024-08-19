package com.elm.viewmodeltest.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class LoginState {
   data object Idle : LoginState()
    data object Loading : LoginState()
    data object Success : LoginState()
   data object Failed : LoginState()
}

class LoginViewModdel:ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> get() = _loginState
    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            delay(2000)
            if (username == "admin" && password == "admin") {
                _loginState.value = LoginState.Success
            }else if(username.length<4 || password.length<4){
                _loginState.value = LoginState.Failed
            }
            else {
                _loginState.value = LoginState.Failed
            }
        }
    }

    fun resetState() {
        _loginState.value = LoginState.Idle
    }

}